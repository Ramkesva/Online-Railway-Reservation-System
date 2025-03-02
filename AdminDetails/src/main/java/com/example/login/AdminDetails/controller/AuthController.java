package com.example.login.AdminDetails.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.AdminDetails.AdminRepository;
import com.example.login.AdminDetails.model.AdminModel;
import com.example.login.AdminDetails.model.AuthenticationRequest;
import com.example.login.AdminDetails.model.AuthenticationResponse;
import com.example.login.AdminDetails.service.UserInfoService;
import com.example.login.AdminDetails.util.JwtUtil;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/admin")
public class AuthController {
	
	@Autowired
	AdminRepository adminrepo;
	
	@Autowired
	AuthenticationManager authenticationmanager;
	
	@Autowired
	UserInfoService userinfoservice;
	
	@Autowired
	JwtUtil jwtutil;
	
	@PostMapping("/register")
	private ResponseEntity<AuthenticationResponse> registerClientToken(@RequestBody AuthenticationRequest authrequest){

		AdminModel usermodel =new AdminModel();

		usermodel.setUsername(authrequest.getUsername());
		usermodel.setPassword(authrequest.getPassword());
		
		try {
			adminrepo.save(usermodel);
		}
		catch(Exception e){
			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse
					("Registration Failed") , HttpStatus.OK);
		}
		
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse
				(authrequest.getUsername()+" registered Successfully "), HttpStatus.OK);
	}
	
	
	@PostMapping("/authenticate")
	private ResponseEntity<?> authenticateClientToken(@RequestBody AuthenticationRequest authrequest) throws Exception{

		try {
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
		}
		
//		catch(BadCredentialsException e) {
//			throw new Exception("Incorrect Username or Password",e);
//		}
		
		catch(Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("no"));
		}
		
		UserDetails userdetails= userinfoservice.loadUserByUsername(authrequest.getUsername());
		
		String jwt = jwtutil.generateToken(userdetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}
	
	@PostMapping("/reg")
	public String adduser(@RequestBody AdminModel adminmodel) {
		adminrepo.save(adminmodel);
		return "User with Id: "+adminmodel.getId()+" have been Registered Successfully";

	}
}