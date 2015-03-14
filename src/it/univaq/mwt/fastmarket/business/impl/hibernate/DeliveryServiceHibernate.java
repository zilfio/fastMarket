package it.univaq.mwt.fastmarket.business.impl.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
import it.univaq.mwt.fastmarket.business.DeliveryService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Delivery;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class DeliveryServiceHibernate implements DeliveryService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public ResponseGrid<Delivery> findAllDeliveriesPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Delivery.class,"d")
				.setFetchMode("d.cart", FetchMode.JOIN)
				.createAlias("d.cart", "cart"); // inner join by default
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("d.id");
		} else if ("cart.name".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("cart.name");
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
			criteria.add(Restrictions.like("cart.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(Delivery.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Delivery> deliveries = criteria.list();
		
		return new ResponseGrid<Delivery>(requestGrid.getsEcho(), records, records, deliveries);
	}
	
	@Transactional
	public void create(Delivery delivery) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(delivery);
	}

	@Transactional
	public Delivery findDeliveryByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Delivery where id=:id");
		query.setParameter("id", id);
		Delivery delivery = (Delivery) query.uniqueResult();
		if(delivery == null) {
			throw new BusinessException("Delivery not found");
		} else {
			return delivery;
		}
	}

	@Transactional
	public void update(Delivery delivery) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(delivery);
	}

	@Transactional
	public void delete(Delivery delivery) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Delivery delivery2 = (Delivery) session.load(Delivery.class, delivery.getId());
		session.delete(delivery2);
	}

}
