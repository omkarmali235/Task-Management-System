package com.omkar.TaskManagementSystem.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	
	/*
	 * We have to implement userprincipal for taking userobject from User to UserBasicDatail
	 */
	
	private Users user;

	public UserPrincipal(Users user) {
		this.user = user;
//		  this.authorities = Arrays.stream(user.getRole().split(","))  //if we have multiple roles then we can use this
//                  .map(SimpleGrantedAuthority::new)
//                  .collect(Collectors.toList());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		System.out.println("Role : "+user.getRole());
		return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));  //if we have to give default user authority ten use this
//		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

}
