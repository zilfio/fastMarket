package it.univaq.mwt.fastmarket.common.spring;

import it.univaq.mwt.fastmarket.business.BusinessException;
import it.univaq.mwt.fastmarket.business.SecurityService;
import it.univaq.mwt.fastmarket.business.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SecurityService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user;
		try {
			user = service.authenticate(username);
		} catch (BusinessException e) {
			throw new UsernameNotFoundException("utente non trovato");
		}

		if (user==null) {
			throw new UsernameNotFoundException("utente non trovato");
		}
		return new UserDetailsImpl(user);

	}

}
