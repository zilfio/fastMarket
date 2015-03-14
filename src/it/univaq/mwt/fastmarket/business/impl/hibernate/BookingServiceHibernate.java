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

import it.univaq.mwt.fastmarket.business.BookingService;
import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Booking;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class BookingServiceHibernate implements BookingService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public ResponseGrid<Booking> findAllBookingsPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Booking.class,"b")
				.setFetchMode("b.cart", FetchMode.JOIN)
				.createAlias("b.cart", "cart"); // inner join by default
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("b.id");
		} else if ("cart.name".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("cart.name");
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
			criteria.add(Restrictions.like("cart.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(Booking.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Booking> bookings = criteria.list();
		
		return new ResponseGrid<Booking>(requestGrid.getsEcho(), records, records, bookings);
	}
	
	@Transactional
	public void create(Booking booking) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(booking);
	}

	@Transactional
	public Booking findBookingByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Booking where id=:id");
		query.setParameter("id", id);
		Booking booking = (Booking) query.uniqueResult();
		if(booking == null) {
			throw new BusinessException("Booking not found");
		} else {
			return booking;
		}
	}

	@Transactional
	public void update(Booking booking) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(booking);
	}

	@Transactional
	public void delete(Booking booking) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Booking booking2 = (Booking) session.load(Booking.class, booking.getId());
		session.delete(booking2);
	}

}
