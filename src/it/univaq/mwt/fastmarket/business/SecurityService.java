package it.univaq.mwt.fastmarket.business;

import it.univaq.mwt.fastmarket.business.model.User;

public interface SecurityService {

	User authenticate(String username) throws BusinessException;
	
}
