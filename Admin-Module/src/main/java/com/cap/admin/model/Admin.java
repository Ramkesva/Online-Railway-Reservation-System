package com.cap.admin.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin {

	@Id
	private String uid;
	private String fname;
	private String mname;
	private String lname;
	private String email;
	private String password;
	
	@DBRef
	private Set<Role> role;
	
	@Indexed(unique = true)
	private String userName;
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Admin(String uid, String fname, String mname, String lname, String email, String password, Set<Role> role,
			String userName) {
		super();
		this.uid = uid;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Admin [uid=" + uid + ", fname=" + fname + ", mname=" + mname + ", lname=" + lname + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", userName=" + userName + "]";
	}

	
	
}
