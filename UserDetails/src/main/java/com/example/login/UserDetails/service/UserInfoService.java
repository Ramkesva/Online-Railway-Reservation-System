package com.example.login.UserDetails.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.login.UserDetails.UserRepository;
import com.example.login.UserDetails.model.UserModel;

@Service
public class UserInfoService implements UserDetailsService{
	
	@Autowired
	UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel availUser=userrepo.findByusername(username);
		if (availUser==null) {
			return null;
		}
		String user=availUser.getUsername();
		String pass=availUser.getPassword();
		return new User(user, pass,new ArrayList<>());
	}

}