package com.cap.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;


public class Role implements GrantedAuthority {

	@Id
	private Long rid;
	@Indexed(unique = true)
	private String role;
	@Override
	public String getAuthority() {
		
		return role;
	}
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Role(Long rid, String role) {
		super();
		this.rid = rid;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", role=" + role + "]";
	}
	

}
