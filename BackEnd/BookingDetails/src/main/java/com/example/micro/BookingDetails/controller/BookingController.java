package com.example.micro.BookingDetails.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.micro.BookingDetails.BookingRepository;
import com.example.micro.BookingDetails.model.BookingModel;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingrepo;
	
	@PostMapping("/bookticket/{userId}")
	public int bookticket(@PathVariable String userId, @RequestBody BookingModel book)
	{
		book.setUserId(userId);
		List<BookingModel> ticketslist = bookingrepo.findByUserId(book.getUserId());
		
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
	
	@PutMapping("/updateorder/{id}")
	public String updateorder(@PathVariable("id") int id, @RequestBody BookingModel book) {
		bookingrepo.save(book);
		return "Train with no. "+id+" havebeen updated successfully";
	}
	
	@DeleteMapping("/cancelticket/{id}")
	public String cancelticket(@PathVariable String id)
	{
		bookingrepo.deleteById(id);
		return "Train Ticket with "+id+" cancelled Succesfully";
	}
	
}
