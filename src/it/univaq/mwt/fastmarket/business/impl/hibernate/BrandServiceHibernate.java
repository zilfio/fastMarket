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

import it.univaq.mwt.fastmarket.business.BrandService;
import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Brand;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class BrandServiceHibernate implements BrandService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
    public Set<Brand> getAllBrands() throws BusinessException {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Brand");
            List<Brand> brands = query.list();
            Set<Brand> result = new HashSet<Brand>(brands);
            return result;
    }
	
	@Transactional
	public ResponseGrid<Brand> findAllBrandsPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Brand.class,"b");
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("b.id");
		} else {
			requestGrid.setSortCol("b." + requestGrid.getSortCol());
		}
	
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("b.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(Brand.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Brand> brands = criteria.list();
		
		return new ResponseGrid<Brand>(requestGrid.getsEcho(), records, records, brands);
	}
	
	@Transactional
	public void create(Brand brand) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(brand);
		
	}

	@Transactional
	public Brand findBrandByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Brand where id=:id");
		query.setParameter("id", id);
		Brand brand = (Brand) query.uniqueResult();
		if(brand == null) {
			throw new BusinessException("Brand not found");
		} else {
			return brand;
		}
	}

	@Transactional
	public void update(Brand brand) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(brand);
	}

	@Transactional
	public void delete(Brand brand) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Brand brand2 = (Brand) session.load(Brand.class, brand.getId());
		session.delete(brand2);
	}

}
