package br.com.gustavoakira.devpay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.gustavoakira.devpay.model.Users;
import br.com.gustavoakira.devpay.service.UsersService;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UsersService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = service.searchEntity(username);
		if(!validateUser(user)) {
			throw new UsernameNotFoundException("User without permission");
		}
		return user;
	}
	
	private boolean validateUser(Users user) {
		boolean result = false;
		
		if(user != null && user.getPermission() != null && user.getActive()) {
			result = true;
		}
		return result;
	}
}
