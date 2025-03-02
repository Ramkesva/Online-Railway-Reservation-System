package com.example.login.AdminDetails.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.login.AdminDetails.AdminRepository;
import com.example.login.AdminDetails.TrainRepository;
import com.example.login.AdminDetails.UserRepository;
import com.example.login.AdminDetails.model.AdminModel;
import com.example.login.AdminDetails.model.TrainModel;
import com.example.login.AdminDetails.model.UserModel;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/admin")
public class AdminController {
	
	    @Autowired
	    private RestTemplate restTemplate;

		@Autowired
		private AdminRepository adminrepo;
		
		@Autowired
		private TrainRepository trainrepo;
		
		@Autowired
		private UserRepository userrepo;
		
//--------------------------------------Admin-CRUD-----------------------------------------
		
		@PostMapping("/registeradmin")
		public String addadmin(@RequestBody AdminModel admin) {
			adminrepo.save(admin);
			return "Admin with Id: "+admin.getId()+" have been Registered Successfully";
		}
		
		@GetMapping("/viewadminprofile/{id}")
		public Optional<AdminModel> getadmin(@PathVariable("id") String id){
			return adminrepo.findById(id);
		}

		@PutMapping("/updateprofile/{id}")
		public String updateadmin(@PathVariable("id") String id, @RequestBody AdminModel adminmodel) {
			adminrepo.save(adminmodel);
			return "Admin with id "+id+" have been updated successfully";
		}
		
		@DeleteMapping("/deleteadmin/{id}")
		public String deleteadmin(@PathVariable String id) {
			adminrepo.deleteById(id);
			return "Admin with id "+id+" have been deleted";
		}
		
//---------------------------------------Admin-User----------------------------------------------------
		
		@GetMapping("/viewallusers")
		public List<UserModel>getallusers() {
			return Arrays.asList(restTemplate.getForObject("http://UserDetails/user/viewallusers",UserModel[].class));
		}
		
		@GetMapping("/viewuser/{id}")
		public List<UserModel> getuser(@PathVariable("id") String id)
		{
			return Arrays.asList(restTemplate.getForObject("http://UserDetails/user/viewuser/"+id,UserModel[].class));	
		}
		
		@PutMapping("/updateuser/{id}")
		public String updateuser(@RequestBody UserModel usermodel, @PathVariable("id") String id) {
			this.restTemplate.put("http://UserDetails/user/updateprofile/{id}",id,usermodel);
			return "User with id : "+id+" have been updated";
		}
	
		@DeleteMapping("/deleteuser/{id}")
		public String deleteuser(@PathVariable String id) {
			this.restTemplate.delete("http://UserDetails/user/deleteprofile/{id}",id);
			return "User with id :"+id+" have been deleted";
		}
		
//--------------------------------------Admin-Train---------------------------------------------
		
		@PostMapping("/addtrain")
		public String addtrain(@RequestBody TrainModel trainmodel) {
			this.restTemplate.postForObject("http://TrainDetails/train/addtrain", trainmodel, TrainModel.class);
			return "Train with No: "+trainmodel.getTrainNo()+" have been added Successfully";
		}
		
		@GetMapping("/viewalltrains")
		public List<TrainModel> getAllTrains()
		{
			return Arrays.asList(restTemplate.getForObject("http://TrainDetails/train/viewalltrains",TrainModel[].class));
		}

		@GetMapping("/viewtrainbyno/{trainNo}")
		public TrainModel getTrains(@PathVariable("trainNo") String trainNo)
		{
			return restTemplate.getForObject("http://TrainDetails/train/viewtrainbyno/"+trainNo,TrainModel.class);	
		}
		
		@GetMapping("/viewtrainbyname/{trainName}")
		public TrainModel getTrainsbyname(@PathVariable("trainName") String trainName)
		{
			return restTemplate.getForObject("http://TrainDetails/train/viewtrainbyname/"+trainName,TrainModel.class);	
		}
		
		@PutMapping("/updatetrain/{trainNo}")
		public String updatetrain(@RequestBody TrainModel trainmodel, @PathVariable String trainNo) {
			this.restTemplate.put("http://TrainDetails/train/updatetrain/{trainNo}",trainmodel,trainNo,TrainModel.class);
			return "Train with no. : "+trainNo+" have been updated";
		}
	
		@DeleteMapping("/deletetrain/{trainNo}")
		public String deletetrain(@PathVariable String trainNo) {
			this.restTemplate.delete("http://TrainDetails/train/deletetrain/"+trainNo,TrainModel.class);
			return "Train with no. :"+trainNo+" have been deleted";
		}
		
	}