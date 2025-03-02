package com.example.login.AdminDetails.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.login.AdminDetails.AdminRepository;
import com.example.login.AdminDetails.model.AdminModel;

@Service
public class UserInfoService implements UserDetailsService{
	
	@Autowired
	AdminRepository adminrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminModel availUser=adminrepo.findByusername(username);
		if (availUser==null) {
			return null;
		}
		String user=availUser.getUsername();
		String pass=availUser.getPassword();
		return new User(user, pass,new ArrayList<>());
	}

}