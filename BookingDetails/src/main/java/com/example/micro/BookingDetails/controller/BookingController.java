package com.example.micro.BookingDetails.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.micro.BookingDetails.BookingRepository;
import com.example.micro.BookingDetails.model.BookingModel;
import com.example.micro.BookingDetails.model.TrainModel;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingrepo;
	
	
	@PostMapping("/bookticket/{userId}/{fare}")
	public int bookticket(@PathVariable String userId, @PathVariable int fare, @RequestBody BookingModel book)
	{
		book.setUserId(userId);
		List<BookingModel> ticketslist = bookingrepo.findByUserId(book.getUserId());
		
		book.setFare(fare*book.getTotalseats());
		int totalseats=0;
		for(int i=0;i<ticketslist.size();i++)
		{
			totalseats += ticketslist.get(i).getTotalseats();
		}
		if(totalseats+book.getTotalseats()<=6)
		{
			bookingrepo.save(book);
			return book.getTotalseats();
		}
		else {
			return 0;
		}
		
		//return book.getTotalseats();
	}
	
//	@GetMapping("/getorder/{userId}")
//	public List<BookingModel> getorder(@PathVariable String userId)
//	{
//		return bookingrepo.findByUserId(userId);
//	}
	
	@GetMapping("/getallorders")
	public List<BookingModel> getAllOrders()
	{
		return bookingrepo.findAll();
	}
	
	@GetMapping("/getorder/{userId}")
	public List<BookingModel> getorder(@PathVariable String userId)
	{
		return bookingrepo.findByUserId(userId);
	}
	
	@GetMapping("/getorderpnr/{pnrId}")
	public BookingModel getorderpnr(@PathVariable String pnrId)
	{
		return bookingrepo.findByPnrId(pnrId);
	}
	
//	@PutMapping("/updateorder/{pnrId}")
//	public String updateorder(@PathVariable("pnrId") String pnrId, @RequestBody BookingModel book) {
//		bookingrepo.save(book);
//		return "Train with no. "+pnrId+" havebeen updated successfully";
//	}
	
	@DeleteMapping("/cancelticket/{pnrId}")
	public String cancelticket(@PathVariable String pnrId)
	{
		bookingrepo.deleteById(pnrId);
		return "Train Ticket with "+pnrId+" cancelled Succesfully";
	}
	
}
