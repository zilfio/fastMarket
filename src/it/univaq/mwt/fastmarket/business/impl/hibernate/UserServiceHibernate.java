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
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.UserService;
import it.univaq.mwt.fastmarket.business.model.User;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;
import it.univaq.mwt.fastmarket.common.utility.MD5;

@Service
public class UserServiceHibernate implements UserService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
    public Set<User> getAllUsers() throws BusinessException {
            Session session = sessionFactory.getCurrentSession();
            List<User> users = session.createQuery("from User").list();
            Set<User> result = new HashSet<User>(users);
            
            return result;
    }
	
	@Transactional
	public ResponseGrid<User> findAllUsersPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(User.class,"u");
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("u.id");
		} else {
			requestGrid.setSortCol("u." + requestGrid.getSortCol());
		}
	
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("u.username", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(User.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<User> users = criteria.list();
		
		return new ResponseGrid<User>(requestGrid.getsEcho(), records, records, users);
	}

	@Transactional
	public void create(User user) throws BusinessException {
		// setto la password in md5
		user.setPassword(MD5.generateMD5(user.getPassword()));
		
		Session session = sessionFactory.getCurrentSession();	
		session.save(user);
	}

	@Transactional
	public User findUserByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User u left join fetch u.roles where u.id=:id");
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		if(user == null) {
			throw new BusinessException("User not found");
		} else {
			return user;
		}
	}
	
	@Transactional
	public User findUserByUsername(String username) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();;
		Query query = session.createQuery("from User u left join fetch u.roles where u.username=:username");
		query.setParameter("username", username);
		User user = (User) query.uniqueResult();
		return user;
	}

	@Transactional
	public void update(User user) throws BusinessException {	
		// recupero utente
		User old_user = findUserByID(user.getId());
		
		// se le password sono diverse allora applico l'md5 altrimenti la lascio invariata
		if(!user.getPassword().equals(old_user.getPassword())) {
			user.setPassword(MD5.generateMD5(user.getPassword()));
		}
				
		Session session = sessionFactory.getCurrentSession();
		session.merge(user);
	}

	@Transactional
	public void delete(User user) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		User user2 = (User) session.load(User.class, user.getId());
		session.delete(user2);
	}

}
