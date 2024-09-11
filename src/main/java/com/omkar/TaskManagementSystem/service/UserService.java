package com.omkar.TaskManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.omkar.TaskManagementSystem.model.Users;
import com.omkar.TaskManagementSystem.repo.UserDetailRepo;

@Service
public class UserService {
	/*
	 * ALl Services  related to the User registration login is provided
	 */
	
	@Autowired
	private AuthenticationManager authManager; // it is referencing Security config authentication manager bean
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailRepo userRepo;
		
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // 12 rounds of encoding 
	
	public String loginUser(Users user) {	
		//it is authenticate username password
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));//it is authenticate username password
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}
		return new UsernameNotFoundException("Username/Password is Incorrect").getMessage();
	}


	public Users registerUser(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

}
