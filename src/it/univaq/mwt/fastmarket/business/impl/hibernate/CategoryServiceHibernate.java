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
import it.univaq.mwt.fastmarket.business.CategoryService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Category;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class CategoryServiceHibernate implements CategoryService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
    public Set<Category> getAllCategories() throws BusinessException {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Category");
            List<Category> categories = query.list();
            Set<Category> result = new HashSet<Category>(categories);
            return result;
    }
	
	@Transactional
	public ResponseGrid<Category> findAllCategoriesPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Category.class,"c");
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("c.id");
		} else {
			requestGrid.setSortCol("c." + requestGrid.getSortCol());
		}
	
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("c.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(Category.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Category> categories = criteria.list();
		
		return new ResponseGrid<Category>(requestGrid.getsEcho(), records, records, categories);
	}
	
	@Transactional
	public void create(Category category) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(category);
	}

	@Transactional
	public Category findCategoryByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category where id=:id");
		query.setParameter("id", id);
		Category category = (Category) query.uniqueResult();
		if(category == null) {
			throw new BusinessException("Category not found");
		} else {
			return category;
		}
	}

	@Transactional
	public void update(Category category) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(category);
	}

	@Transactional
	public void delete(Category category) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Category category2 = (Category) session.load(Category.class, category.getId());
		session.delete(category2);
	}

}
