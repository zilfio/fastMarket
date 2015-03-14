package it.univaq.mwt.fastmarket.business;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.model.Category;

public interface CategoryService {
	
	Set<Category> getAllCategories() throws BusinessException;
	
	ResponseGrid<Category> findAllCategoriesPaginated(RequestGrid requestGrid) throws BusinessException;
	
	void create(Category category) throws BusinessException;
	
	Category findCategoryByID(Long id) throws BusinessException;
	
	void update(Category category) throws BusinessException;
	
	void delete(Category category) throws BusinessException;
	
}
