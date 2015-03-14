package it.univaq.mwt.fastmarket.business.impl.hibernate;

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

import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.DistrictService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.District;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class DistrictServiceHibernate implements DistrictService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
    public Set<District> getAllDistricts() throws BusinessException {
            Session session = sessionFactory.getCurrentSession();
            List<District> districts = session.createQuery("from District").list();
            Set<District> result = new HashSet<District>(districts);
            
            return result;
    }
	
	@Transactional
	public ResponseGrid<District> findAllDistrictsPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(District.class,"d");
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("d.id");
		} else {
			requestGrid.setSortCol("d." + requestGrid.getSortCol());
		}
	
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("d.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(District.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<District> districts = criteria.list();
		
		return new ResponseGrid<District>(requestGrid.getsEcho(), records, records, districts);
	}
	
	@Transactional
	public void create(District district) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(district);
	}

	@Transactional
	public District findDistrictByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from District where id=:id");
		query.setParameter("id", id);
		District district = (District) query.uniqueResult();
		if(district == null) {
			throw new BusinessException("District not found");
		} else {
			return district;
		}
	}

	@Transactional
	public void update(District district) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(district);
	}

	@Transactional
	public void delete(District district) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		District district2 = (District) session.load(District.class, district.getId());
		session.delete(district2);
	}	
}
