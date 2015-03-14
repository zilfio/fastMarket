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
import it.univaq.mwt.fastmarket.business.RoleService;
import it.univaq.mwt.fastmarket.business.model.Role;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

@Service
public class RoleServiceHibernate implements RoleService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
    public Set<Role> getAllRoles() throws BusinessException {
            Session session = sessionFactory.getCurrentSession();
            List<Role> roles = session.createQuery("from Role").list();
            Set<Role> result = new HashSet<Role>(roles);

            return result;
    }
	
	@Transactional
	public ResponseGrid<Role> findAllRolesPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Role.class,"r");
		
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("r.id");
		} else {
			requestGrid.setSortCol("r." + requestGrid.getSortCol());
		}
	
		if(!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) {
			if("asc".equals(requestGrid.getSortDir())){
				criteria.addOrder(Order.asc(requestGrid.getSortCol()));	
			}else{
				criteria.addOrder(Order.desc(requestGrid.getSortCol()));	
			}
		}
		
		if(!"".equals(requestGrid.getsSearch())){
			criteria.add(Restrictions.like("r.name", ConversionUtility.addPercentSuffix(requestGrid.getsSearch())).ignoreCase());
		}
		
		criteria.setFirstResult((int) requestGrid.getiDisplayStart())
			.setMaxResults((int) requestGrid.getiDisplayLength());
		
		Long records = (Long) session.createCriteria(Role.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Role> roles = criteria.list();
		
		return new ResponseGrid<Role>(requestGrid.getsEcho(), records, records, roles);
	}

	@Transactional
	public void create(Role role) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
	}

	@Transactional
	public Role findRoleByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role where id=:id");
		query.setParameter("id", id);
		Role role = (Role) query.uniqueResult();
		if(role == null) {
			throw new BusinessException("Role not found");
		} else {
			return role;
		}
	}
	
	@Transactional
	public Role findRoleByName(String name) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role where name=:name");
		query.setParameter("name", name);
		Role role = (Role) query.uniqueResult();
		return role;
	}

	@Transactional
	public Set<Role> getRolesByUserID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select roles from User u where u.id = :id");
		query.setParameter("id", id);
		List<Role> roles = query.list();
		Set<Role> result = new HashSet<Role>(roles);
		
		return result;
	}
	
	@Transactional
	public Set<Role> getRolesUserRegistred() throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		
		if(findRoleByName("UserRegistred") == null) {
			Role role = new Role(0, "UserRegistred", "");
			create(role);
		}
		
		Query query = session.createQuery("from Role where name = :name");
		query.setParameter("name", "UserRegistred");
		List<Role> roles = query.list();
		Set<Role> result = new HashSet<Role>(roles);
		
		return result;
	}
	
	@Transactional
	public void update(Role role) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(role);
	}

	@Transactional
	public void delete(Role role) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Role role2 = (Role) session.load(Role.class, role.getId());
		session.delete(role2);
	}

}
