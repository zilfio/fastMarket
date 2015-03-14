package it.univaq.mwt.fastmarket.business.impl.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.RegionService;
import it.univaq.mwt.fastmarket.business.RequestGrid;
import it.univaq.mwt.fastmarket.business.ResponseGrid;
import it.univaq.mwt.fastmarket.business.model.Region;
import it.univaq.mwt.fastmarket.common.utility.ConversionUtility;

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

@Service
public class RegionServiceHibernate implements RegionService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
    public Set<Region> getAllRegions() throws BusinessException {
            Session session = sessionFactory.getCurrentSession();
            List<Region> regions = session.createQuery("from Region").list();
            Set<Region> result = new HashSet<Region>(regions);
            
            return result;
    }
	
	@Transactional
	public ResponseGrid<Region> findAllRegionsPaginated(RequestGrid requestGrid) throws BusinessException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Region.class,"r");
		
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
		
		Long records = (Long) session.createCriteria(Region.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		List<Region> regions = criteria.list();
		
		return new ResponseGrid<Region>(requestGrid.getsEcho(), records, records, regions);
	}
	
	@Transactional
	public void create(Region region) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.save(region);
	}

	@Transactional
	public Region findRegionByID(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Region where id=:id");
		query.setParameter("id", id);
		Region region = (Region) query.uniqueResult();
		if(region == null) {
			throw new BusinessException("Region not found");
		} else {
			return region;
		}
	}

	@Transactional
	public void update(Region region) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		session.merge(region);
	}

	@Transactional
	public void delete(Region region) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Region region2 = (Region) session.load(Region.class, region.getId());
		session.delete(region2);
	}

}
