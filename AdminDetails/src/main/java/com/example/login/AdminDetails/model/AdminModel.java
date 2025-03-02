package com.example.login.AdminDetails.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="admindata")
public class AdminModel {
	
	@Id
	private String id;
	private String username;
	private String password;
	
	public AdminModel() {

	}
	
	public AdminModel(String id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
