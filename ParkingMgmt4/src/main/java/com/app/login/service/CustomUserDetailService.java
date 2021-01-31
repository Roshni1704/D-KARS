package com.app.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.models.User;
import com.app.service.ICustomerService;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private ICustomerService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = customerService.findByName(username);
		
		CustomUserDetails userDetails = null;
		
		if(user != null)
		{
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
		}
		
		else
		{
			throw new UsernameNotFoundException("Customer does not exists with the name" + username);
		}
			
		return userDetails;
	}

}
