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
import it.univaq.mwt.fastmarket.business.IntoleranceCategoryService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.IntoleranceCategory;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class IntoleranceCategoryServiceHibernate implements IntoleranceCategoryService{

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
    public Set<IntoleranceCategory> getAllIntoleranceCategories() throws BusinessException {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from IntoleranceCategory");
            List<IntoleranceCategory> intoleranceCategories = query.list();
            Set<IntoleranceCategory> result = new HashSet<IntoleranceCategory>(intoleranceCategories);
            return result;
    }
	
	@Transactional
	public ResponseGrid<IntoleranceCategory> findAllIntoleranceCategoriesPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(IntoleranceCategory.class,"ic");
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("ic.id");
		} else {
			requestGrid.setSortCol("ic." + requestGrid.getSortCol());
		}
	
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("ic.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(IntoleranceCategory.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<IntoleranceCategory> intoleranceCategories = criteria.list();
		
		return new ResponseGrid<IntoleranceCategory>(requestGrid.getsEcho(), records, records, intoleranceCategories);
	}
	
	@Transactional
	public void create(IntoleranceCategory intoleranceCategory) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(intoleranceCategory);
	}

	@Transactional
	public IntoleranceCategory findIntoleranceCategoryById(Long id)
			throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from IntoleranceCategory where id=:id");
		query.setParameter("id", id);
		IntoleranceCategory intoleranceCategory = (IntoleranceCategory) query.uniqueResult();
		if(intoleranceCategory == null) {
			throw new BusinessException("IntoleranceCategory not found");
		} else {
			return intoleranceCategory;
		}
	}

	@Transactional
	public void update(IntoleranceCategory intoleranceCategory) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(intoleranceCategory);
	}

	@Transactional
	public void delete(IntoleranceCategory intoleranceCategory) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		IntoleranceCategory intoleranceCategory2 = (IntoleranceCategory) session.load(IntoleranceCategory.class, intoleranceCategory.getId());
		session.delete(intoleranceCategory2);
	}

}
