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

import it.univaq.mwt.fastmarket.business.AddressService;
import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Address;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class AddressServiceHibernate implements AddressService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public ResponseGrid<Address> findAllAddressesPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Address.class,"a");
		
		// ordinamento colonne
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("a.id");
		} else {
			requestGrid.setSortCol("a." + requestGrid.getSortCol());
		}
	
		// ordinamento colonna: asc o disc
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		// casella di ricerca
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("a.street", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(Address.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Address> addresses = criteria.list();
		
		return new ResponseGrid<Address>(requestGrid.getsEcho(), records, records, addresses);
	}

	@Transactional
	public void create(Address address) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(address);
	}

	@Transactional
	public Address findAddressByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Address where id=:id");
		query.setParameter("id", id);
		Address address = (Address) query.uniqueResult();
		if(address == null) {
			throw new BusinessException("Address not found");
		} else {
			return address;
		}
	}

	@Transactional
	public void update(Address address) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(address);
	}

	@Transactional
	public void delete(Address address) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Address address2 = (Address) session.load(Address.class, address.getId());
		session.delete(address2);
	}

}
