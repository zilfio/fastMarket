package it.univaq.mwt.fastmarket.business.impl.hibernate;

import java.util.List;

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
import it.univaq.mwt.fastmarket.business.GroceryService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Grocery;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class GroceryServiceHibernate implements GroceryService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public ResponseGrid<Grocery> findAllGroceriesPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Grocery.class,"g");
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("g.id");
		} else {
			requestGrid.setSortCol("g." + requestGrid.getSortCol());
		}
	
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("g.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(Grocery.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Grocery> groceries = criteria.list();
		
		return new ResponseGrid<Grocery>(requestGrid.getsEcho(), records, records, groceries);
	}
	
	@Transactional
	public void create(Grocery grocery) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(grocery);
	}

	@Transactional
	public Grocery findGroceryByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Grocery where id=:id");
		query.setParameter("id", id);
		Grocery grocery = (Grocery) query.uniqueResult();
		if(grocery == null) {
			throw new BusinessException("Grocery not found");
		} else {
			return grocery;
		}
	}

	@Transactional
	public void update(Grocery grocery) throws BusinessException {		
		Session session = sessionFactory.getCurrentSession();
		session.merge(grocery);
	}

	@Transactional
	public void delete(Grocery grocery) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Grocery grocery2 = (Grocery) session.load(Grocery.class, grocery.getId());
		session.delete(grocery2);
	}

}
