package it.univaq.mwt.fastmarket.business;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.model.Region;

public interface RegionService {
	
	Set<Region> getAllRegions() throws BusinessException;
	
	ResponseGrid<Region> findAllRegionsPaginated(RequestGrid requestGrid) throws BusinessException;
	
	void create(Region region) throws BusinessException;
	
	Region findRegionByID(Long id) throws BusinessException;
	
	void update(Region region) throws BusinessException;
	
	void delete(Region region) throws BusinessException;
}
