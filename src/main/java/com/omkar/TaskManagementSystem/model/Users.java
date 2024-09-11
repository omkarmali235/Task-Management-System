package com.omkar.TaskManagementSystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {
	/*
	 * Entity class for Table Creation
	 */
	
	@Id
	int id;
	String username;
	String password;
	String role;
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Users(int id, String username, String password,String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}


	public Users() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}
	
}
