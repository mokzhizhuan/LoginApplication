package com.loginapp.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.loginapp.admin.UserRepository;
import com.loginapp.users.entity.User;

public class UsersDetailsService implements UserDetailsService {

	@Autowired
	public UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.getUserByEmail(email);
		if (user != null) {
			return new UsersDetails(user);
		}
		else
		{
			throw new UsernameNotFoundException("Could not find user with email: " + email);
		}
	}

}
