package it.univaq.mwt.fastmarket.business.impl.hibernate;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.ProductService;
import it.univaq.mwt.fastmarket.business.model.Brand;
import it.univaq.mwt.fastmarket.business.model.Category;
import it.univaq.mwt.fastmarket.business.model.Image;
import it.univaq.mwt.fastmarket.business.model.IntoleranceCategory;
import it.univaq.mwt.fastmarket.business.model.Product;
import it.univaq.mwt.fastmarket.common.utility.Config;

@Service
public class ProductServiceHibernate implements ProductService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public Set<Product> getAllProducts() throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product");
		List<Product> products = query.list();
		Set<Product> result = new HashSet<Product>(products);
		return result;
	}
	
	@Transactional
	public Product findProductById(Long id) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product where id=:id");
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		return product;
	}
	
	@Transactional
	public Set<Product> getLastProducts(int start, int maxRows) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product order by id DESC");
		query.setFirstResult(start);
		query.setMaxResults(maxRows);
		List<Product> products = query.list();
		Set<Product> result = new HashSet<Product>(products);
		return result;
	}
	
	@Transactional
	public Set<Product> searchProductByName(String name) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product where name LIKE :name");
		query.setString("name", "%"+name+"%");
		List<Product> products = query.list();
		Set<Product> result = new HashSet<Product>(products);
		return result;
	}
	
	@Transactional
	public Set<Product> searchProductByCategory(Long category) throws BusinessException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product where category.id=:category");
		query.setParameter("category", category);
		List<Product> products = query.list();
		Set<Product> result = new HashSet<Product>(products);
		return result;
	}
	
	@Transactional
	public Set<Product> searchAdvancedProduct(Brand brand, Category category, IntoleranceCategory intoleranceCategory) throws BusinessException {
		String queryWhere = "WHERE";
		String baseQuery;
		
		if (brand != null) {
			queryWhere = queryWhere + " brand.id="+brand.getId()+" AND";
		}
		
		if(category != null) {
			queryWhere = queryWhere + " category.id="+category.getId()+" AND";
		}
		
		if(intoleranceCategory != null) {
			queryWhere = queryWhere + " intoleranceCategory.id="+intoleranceCategory.getId()+" AND";
			baseQuery = "from Grocery ";
		} else {
			baseQuery = "from Product ";
		}
		queryWhere = queryWhere + " id>0";
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(baseQuery + queryWhere);
		List<Product> products = query.list();
		Set<Product> result = new HashSet<Product>(products);
		return result;
	}
	
	@Transactional
	public String saveProductImageIntoFileSystem(Image image) throws BusinessException {
		
		String rootPath = Config.getSetting("upload.dir");
		String directory = "prodotti";
		
		File dir = new File(rootPath + File.separator + directory);
		
		if (!dir.exists())
			dir.mkdirs();
		
		File file = new File(rootPath + File.separator + directory + File.separator + UUID.randomUUID() + ".png");
        try {
            FileUtils.writeByteArrayToFile(file, image.getImage());
            String path = directory + File.separator + file.getName();
            return path;
        } catch (IOException ex) {
            throw new BusinessException("errore nel salvataggio del file" + file.getAbsolutePath(), ex);
        }
	}
	
	@Transactional
	public boolean deleteProductImageIntoFileSystem(String path) throws BusinessException {
		if (path.isEmpty()) {
			return true;
		}
		else {
			String rootPath = Config.getSetting("upload.dir");
			
			File file = new File(rootPath + File.separator + path);
			
			if(file.delete()) {
				return true;
			} else {
				return false;
			}
		}
	}

}
