package com.cap.admin.model;

public class UserRequest {
 
	private String username;
	private String password;
	
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequest [username=" + username + ", password=" + password + "]";
	}
	
	
	
}
