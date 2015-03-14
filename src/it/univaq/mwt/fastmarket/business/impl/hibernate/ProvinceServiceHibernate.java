package it.univaq.mwt.fastmarket.business.impl.hibernate;

import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.ProvinceService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Province;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProvinceServiceHibernate implements ProvinceService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
    public Set<Province> getAllProvinces() throws BusinessException {
            Session session = sessionFactory.getCurrentSession();
            List<Province> provinces = session.createQuery("from Province").list();
            Set<Province> result = new HashSet<Province>(provinces);
            
            return result;
    }
	
	@Transactional
	public ResponseGrid<Province> findAllProvincesPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Province.class,"p");
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("p.id");
		} else {
			requestGrid.setSortCol("p." + requestGrid.getSortCol());
		}
	
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("p.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(Province.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Province> provinces = criteria.list();
		
		return new ResponseGrid<Province>(requestGrid.getsEcho(), records, records, provinces);
	}
	
	@Transactional
	public void create(Province province) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(province);
	}

	@Transactional
	public Province findProvinceByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Province where id=:id");
		query.setParameter("id", id);
		Province province = (Province) query.uniqueResult();
		if(province == null) {
			throw new BusinessException("Province not found");
		} else {
			return province;
		}
	}

	@Transactional
	public void update(Province province) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(province);
	}

	@Transactional
	public void delete(Province province) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Province province2 = (Province) session.load(Province.class, province.getId());
		session.delete(province2);
	}
	
}
