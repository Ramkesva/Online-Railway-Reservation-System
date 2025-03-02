package com.example.login.UserDetails.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.login.UserDetails.UserRepository;
import com.example.login.UserDetails.model.AuthenticationRequest;
import com.example.login.UserDetails.model.AuthenticationResponse;
import com.example.login.UserDetails.model.UserModel;
import com.example.login.UserDetails.service.UserInfoService;
import com.example.login.UserDetails.util.JwtUtil;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/user")
public class AuthController {
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	AuthenticationManager authenticationmanager;
	
	@Autowired
	UserInfoService userinfoservice;
	
	@Autowired
	JwtUtil jwtutil;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
	
	@PostMapping("/register")
	private ResponseEntity<AuthenticationResponse>registerClientToken(@RequestBody AuthenticationRequest authrequest){

		UserModel usermodel =new UserModel();
		usermodel.setUsername(authrequest.getUsername());
		usermodel.setPassword(authrequest.getPassword());
		
		List<UserModel> usermodellist = Arrays.asList(restTemplate.getForObject("http://UserDetails/user/viewallusers",UserModel[].class));
		System.out.println(usermodellist);
		System.out.println(usermodel.getUsername());
		int count = 0;
		for(int i=0;i<usermodellist.size();i++)
		{
			System.out.println(usermodellist.get(i).getUsername());
			if(usermodel.getUsername().equals(usermodellist.get(i).getUsername())) {
				count++;
				break;
			}
				
		}
		
		 if (count==0) {
			 userrepo.save(usermodel);

		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse
				(authrequest.getUsername()+" registered Successfully ","1"), HttpStatus.OK);
	}
	  else {
				System.out.println("Invalid Credentials");
				return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse
					("Registration Failed","0") , HttpStatus.OK);
		}
     
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
			String success = "false";
			return ResponseEntity.ok(new AuthenticationResponse("no",success));
		}
		
		UserDetails userdetails= userinfoservice.loadUserByUsername(authrequest.getUsername());
		
		String jwt = jwtutil.generateToken(userdetails);
		String success = "true";
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt ,success));
	}
	
	@GetMapping("/viewprofile")
	public List<UserModel> getuser(){
		return userrepo.findAll();
	}
	
	@GetMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}
	
	
	@PostMapping("/reg")
	public String adduser(@RequestBody UserModel usermodel) {
		userrepo.save(usermodel);
		return "User with Id: "+usermodel.getId()+" have been Registered Successfully";

	}
}