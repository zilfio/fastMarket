package it.univaq.mwt.fastmarket.business.impl.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.SecurityService;
import it.univaq.mwt.fastmarket.business.model.Role;
import it.univaq.mwt.fastmarket.business.model.User;

@Service
public class SecurityServiceHibernate implements SecurityService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public User authenticate(String username) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username = :username");
		query.setParameter("username", username);
		User user = (User) query.uniqueResult();
		user.setRoles(findRoles(username));
		return user;
	}
	
	private Set<Role> findRoles(String username) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select roles from User u where u.username = :username");
		query.setParameter("username", username);
		List<Role> roles = query.list();
		Set<Role> result = new HashSet<Role>(roles);
		
		return result;
	}

}
