package it.univaq.mwt.fastmarket.business;

import it.univaq.mwt.fastmarket.business.model.CartLine;

public interface CartLineService {
	
	ResponseGrid<CartLine> findAllCartLinesPaginated(RequestGrid requestGrid) throws BusinessException;
	
	void create(CartLine cartLine) throws BusinessException;
	
	CartLine findCartLineById(Long id) throws BusinessException;
	
	void update(CartLine cartLine) throws BusinessException;
	
	void delete(CartLine cartLine) throws BusinessException;
	
}
