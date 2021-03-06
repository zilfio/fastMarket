package it.univaq.mwt.fastmarket.business;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.model.District;

public interface DistrictService {
	
	Set<District> getAllDistricts() throws BusinessException;
	
	ResponseGrid<District> findAllDistrictsPaginated(RequestGrid requestGrid) throws BusinessException;
	
	void create(District district) throws BusinessException;
	
	District findDistrictByID(Long id) throws BusinessException;
	
	void update(District district) throws BusinessException;
	
	void delete(District district) throws BusinessException;
	
}
