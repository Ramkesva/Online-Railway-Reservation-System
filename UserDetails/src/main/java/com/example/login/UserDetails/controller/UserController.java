package com.example.login.UserDetails.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.example.login.UserDetails.UserRepository;
import com.example.login.UserDetails.model.BookingModel;
import com.example.login.UserDetails.model.TrainModel;
import com.example.login.UserDetails.model.UserModel;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

		@Autowired
		private UserRepository userrepo;
		
		@Autowired
		private RestTemplate restTemplate;
		
//--------------------------------------User-CRUD-----------------------------------------
		
//		@PostMapping("/registeruser")
//		public String adduser(@RequestBody UserModel user) {
//			
//			userrepo.save(user);
//			return "User with Id: "+user.getId()+" have been Registered Successfully";
//		}
		
		@GetMapping("/viewuserprofile/{id}")
		public Optional<UserModel> getuser(@PathVariable("id") String id){
			return userrepo.findById(id);
		}

		@RequestMapping(value="/updateprofile/{id}", method=RequestMethod.PUT)
		public String update(@PathVariable("id") String id, @RequestBody UserModel usermodel) {
			userrepo.save(usermodel);
			return "Updated";
		}
	
		@DeleteMapping("/deleteprofile/{id}")
		public String delete(@PathVariable String id) {
			userrepo.deleteById(id);
			return "User with id "+id+" have been deleted";
		}
		
		//checking
		@GetMapping("/viewallusers")
		public List<UserModel>getuser(){
			return userrepo.findAll();
		}
		
//---------------------------------------User-Train---------------------------------------------
		
		@GetMapping("/viewalltrains")
		public List<TrainModel> getAllTrains()
		{
			return Arrays.asList(restTemplate.getForObject("http://TrainDetails/train/viewalltrains",TrainModel[].class));
		}

		@GetMapping("/viewtrain/{trainNo}")
		public TrainModel getTrains(@PathVariable("trainNo") String trainNo)
		{
			return restTemplate.getForObject("http://TrainDetails/train/viewtrainbyno/"+trainNo,TrainModel.class);	
		}
		
		@GetMapping("/findbw/{trainFrom}/{trainTo}")
		public TrainModel findByloc(@PathVariable("trainFrom") String trainFrom, @PathVariable("trainTo") String trainTo)
		{
			return this.restTemplate.getForObject("http://TrainDetails/train/findbw/"+trainFrom+"/"+trainTo,TrainModel.class);
		}
		
		@GetMapping("/findfareno/{trainNo}")
		public int findfare(@PathVariable("trainNo") String trainNo)
		{
			return restTemplate.getForObject("http://TrainDetails/train/findfarebyno/"+trainNo,Integer.class);
		}
		
		@GetMapping("/findfarename/{trainName}")
		public int findfarebyname(@PathVariable("trainName") String trainName)
		{
			return restTemplate.getForObject("http://TrainDetails/train/findfarebyname/"+trainName,Integer.class);
		}
		
		@GetMapping("/findtimeno/{trainNo}")
		public String findtime(@PathVariable("trainNo") String trainNo)
		{
			return restTemplate.getForObject("http://TrainDetails/train/findtimebyno/"+trainNo,String.class);
		}
		
		@GetMapping("/findtimename/{trainName}")
		public String findtimebyname(@PathVariable("trainName") String trainName)
		{
			return restTemplate.getForObject("http://TrainDetails/train/findtimebyname/"+trainName,String.class);
		}		

//-----------------------------------User-Ticket-------------------------------------------------
		
		@PostMapping("/bookticket")
		public String bookticket(@RequestBody BookingModel book)
		{
			TrainModel trainModel = restTemplate.getForObject("http://TrainDetails/train/viewtrainbyno/"+book.getTrainNo(),TrainModel.class);
			int fare = trainModel.getFare();
			
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();
			int seats = restTemplate.postForObject("http://BookingDetails/booking/bookticket/"+username+"/"+fare, book, Integer.class);
//			return seats+"";
			if(seats>0)
			{
			restTemplate.postForObject("http://TrainDetails/train/decreaseseat/"+book.getTrainNo()+"/"+seats,book, BookingModel.class);
			return "Train have been booked Successfully with seats: "+seats;
			}
			else
			{
				return "Limit Reached";
		}
	
		}
		
		@DeleteMapping("/cancelticket/{pnrId}")
		public String cancelticket(@PathVariable String pnrId)
		{
			BookingModel bookingModel = restTemplate.getForObject("http://BookingDetails/booking/getorderpnr/"+pnrId,BookingModel.class);
			TrainModel trainModel = restTemplate.getForObject("http://TrainDetails/train/viewtrainbyno/"+bookingModel.getTrainNo(),TrainModel.class);
			this.restTemplate.delete("http://BookingDetails/booking/cancelticket/"+pnrId, BookingModel.class);
			restTemplate.postForObject("http://TrainDetails/train/increaseseat/"+trainModel.getTrainNo()+"/"+bookingModel.getTotalseats(),bookingModel, BookingModel.class);
			
			return "Train Ticket with "+pnrId+" cancelled Succesfully";
		}
		
		
		
		//UNNECESSARY
		@GetMapping("/getallorders")
		public List<BookingModel> getAllOrder()
		{
			return Arrays.asList(restTemplate.getForObject("http://BookingDetails/booking/getallorders",BookingModel[].class));
		}
		
		@GetMapping("/getorder/{userId}")
		public List<BookingModel> getorder(@PathVariable("userId") String userId)
		{
			return Arrays.asList(restTemplate.getForObject("http://BookingDetails/booking/getorder/"+userId,BookingModel[].class));
		}
		
		@PutMapping("/updateorder/{userId}")
		public String updateorder(@PathVariable String userId, @RequestBody BookingModel book) {
			restTemplate.put("http://BookingDetails/booking/updateorder/{userId}",userId,book);
			return "Order with id "+userId+" havebeen updated Successfully";
		}
		
		
	}