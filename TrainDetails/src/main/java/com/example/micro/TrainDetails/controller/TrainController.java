package com.example.micro.TrainDetails.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

import com.example.micro.TrainDetails.TrainRepository;
import com.example.micro.TrainDetails.model.BookingModel;
import com.example.micro.TrainDetails.model.TrainModel;


@Transactional
@RestController
@CrossOrigin
@RequestMapping("/train")
public class TrainController {
	
	@Autowired
	TrainRepository trainrepo;
	
	@PostMapping("/addtrain")
	public void addtrain(@RequestBody TrainModel trainmodel) {
		trainrepo.save(trainmodel);
		
	}
	
	@GetMapping("/viewalltrains")
	public List<TrainModel> getAllTrains()
	{
		return trainrepo.findAll();
	}

	@GetMapping("/viewtrainbyno/{trainNo}")
	public TrainModel getTrains(@PathVariable("trainNo") String trainNo)
	{
		return trainrepo.findByTrainNo(trainNo);		
	}
	
	@GetMapping("/viewtrainbyname/{trainName}")
	public List<TrainModel> getTrainsbyname(@PathVariable("trainName") String trainName)
	{
		return trainrepo.findByTrainName(trainName);		
	}
	
	@RequestMapping(value="/updatetrain/{trainNo}", method=RequestMethod.PUT)
	public String update(@PathVariable("trainNo") String trainNo, @RequestBody TrainModel trainmodel) {
		trainrepo.save(trainmodel);
		return "Train with no. "+trainNo+" havebeen updated successfully";
	}
	
	
	@DeleteMapping("/deletetrain/{trainNo}")
	
	public void delete(@PathVariable String trainNo)
	{
		TrainModel obj = new TrainModel();
		obj=trainrepo.findByTrainNo(trainNo);
		trainrepo.delete(obj);
	}
	
	@GetMapping("/findbw/{trainFrom}/{trainTo}")
	public List<TrainModel> findByloc(@PathVariable("trainFrom") String trainFrom, @PathVariable("trainTo") String trainTo)
	{
		return trainrepo.findByTrainFromAndTrainTo(trainFrom,trainTo);
	}
	
	@GetMapping("/findfrom/{trainFrom}")
	public List<TrainModel> findByfrom(@PathVariable("trainFrom") String trainFrom)
	{
		return trainrepo.findByTrainFrom(trainFrom);
	}
	
	@GetMapping("/findto/{trainTo}")
	public List<TrainModel> findByto(@PathVariable("trainTo") String trainTo)
	{
		return trainrepo.findByTrainTo(trainTo);
	}
	
//	@GetMapping("/findfarebyname/{trainName}")
//	public int findfarebyname(@PathVariable("trainName") String trainName)
//	{
//		return trainrepo.findByTrainName(trainName).getFare();
//	}
	
	@GetMapping("/findfarebyno/{trainNo}")
	public int findfare(@PathVariable("trainNo") String trainNo)
	{
		return trainrepo.findByTrainNo(trainNo).getFare();
	}
	
	@PostMapping("/decreaseseat/{trainNo}/{seats}")
	public void decreaseseats(@PathVariable("trainNo") String trainNo, @PathVariable("seats") int seats, @RequestBody BookingModel bookmodel)
	{
		TrainModel obj = new TrainModel();
		obj=trainrepo.findByTrainNo(trainNo);
		int temp = obj.getSeats();
		obj.setSeats(temp-seats);
		trainrepo.save(obj);
	}
	
	@PostMapping("/increaseseat/{trainNo}/{seats}")
	public void increaseseats(@PathVariable("trainNo") String trainNo, @PathVariable("seats") int seats)
	{
		TrainModel obj = new TrainModel();
		obj=trainrepo.findByTrainNo(trainNo);
		int temp = obj.getSeats();
		obj.setSeats(temp+seats);
		trainrepo.save(obj);
	}
	
	@GetMapping("/findtimebyno/{trainNo}")
	public String findtimebyno(@PathVariable("trainNo") String trainNo)
	{
		return trainrepo.findByTrainNo(trainNo).getTime();
	}
	
	@GetMapping("/findtimebyname/{trainName}")
	public String findtimebyname(@PathVariable("trainName") String trainName)
	{
		return trainrepo.findByTrainNo(trainName).getTime();
	}
	
}
