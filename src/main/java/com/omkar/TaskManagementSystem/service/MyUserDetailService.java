package com.omkar.TaskManagementSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.omkar.TaskManagementSystem.model.UserPrincipal;
import com.omkar.TaskManagementSystem.model.Users;
import com.omkar.TaskManagementSystem.repo.UserDetailRepo;


@Service
public class MyUserDetailService implements UserDetailsService{
	
	/*
	 * We have to Create this class because we Used UserDetailsService to implement Basic Authentication
	 * But UserDetailsService itself is a interface so we have to implement that interface
	 */
	
	@Autowired
	UserDetailRepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //overrided method
		Users user = repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("UserName Not Found");
		}
		// Log the username and password
        System.out.println("Username from DB: " + user.getUsername());
//        user.setPassword(encoder.encode(user.getPassword())); not working
        System.out.println("Password from DB: " + user.getPassword());
        System.out.println("Username from front: "+username);
        
		return new UserPrincipal(user);
	}

}
