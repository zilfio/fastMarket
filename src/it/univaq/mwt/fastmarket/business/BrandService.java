package it.univaq.mwt.fastmarket.business;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.model.Brand;

public interface BrandService {
	
	Set<Brand> getAllBrands() throws BusinessException;
	
	ResponseGrid<Brand> findAllBrandsPaginated(RequestGrid requestGrid) throws BusinessException;
	
	void create(Brand brand) throws BusinessException;
	
	Brand findBrandByID(Long id) throws BusinessException;
	
	void update(Brand brand) throws BusinessException;
	
	void delete(Brand brand) throws BusinessException;
	
}
