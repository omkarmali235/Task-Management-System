package com.omkar.TaskManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.TaskManagementSystem.model.Users;
import com.omkar.TaskManagementSystem.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String homePage() {
		return "Hello";
	}
	
	@PostMapping("/loginUser")
	public String loginExistingUser(@RequestParam String username, @RequestParam String password) {
		Users user= new Users();
		user.setId(10);
		user.setPassword(password);
		user.setUsername(username);
		return userService.loginUser(user);
	}
	
	@PostMapping("/registerUser")
	public Users register(@RequestParam Users user) {
		return userService.registerUser(user);
	}
	
	
	@GetMapping("/admin")
	public String admin() {
		return "Admin";
	}
	
	@GetMapping("/user")
	public String user() {
		System.out.println("Entered");
		return "user";
	}
	
	@GetMapping("/demo")
	@PreAuthorize("hasRole('ADMIN')")
	public String demp() {
		return "demo";
	}
	
}
