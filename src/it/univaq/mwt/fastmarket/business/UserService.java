package it.univaq.mwt.fastmarket.business;

import java.util.Set;

import it.univaq.mwt.fastmarket.business.model.User;

public interface UserService {
	
	Set<User> getAllUsers() throws BusinessException;
	
	ResponseGrid<User> findAllUsersPaginated(RequestGrid requestGrid) throws BusinessException;
	
	void create(User user) throws BusinessException;
	
	User findUserByID(Long id) throws BusinessException;
	
	User findUserByUsername(String username) throws BusinessException;
	
	void update(User user) throws BusinessException;
	
	void delete(User user) throws BusinessException;
	
}
