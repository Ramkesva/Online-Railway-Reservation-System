//package com.example.micro.TrainDetails;
//
//	import static org.junit.jupiter.api.Assertions.assertEquals;
//	import static org.mockito.Mockito.when;
//
//	import java.util.ArrayList;
//	import java.util.List;
//	import java.util.Optional;
//	import java.util.stream.Collectors;
//	import java.util.stream.Stream;
//
//	import org.junit.jupiter.api.Test;
//	import org.mockito.InjectMocks;
//	import org.mockito.Mock;
//	import org.springframework.boot.test.context.SpringBootTest;
//	import org.springframework.boot.test.context.TestConfiguration;
//
//import com.example.micro.TrainDetails.TrainRepository;
//import com.example.micro.TrainDetails.controller.TrainController;
//import com.example.micro.TrainDetails.model.TrainModel;
//
//	
//	@TestConfiguration
//	@SpringBootTest
//	public class TrainControllerTest {
//		
//		@Mock
//		private TrainRepository trainRepo;
//
//		@InjectMocks
//		private TrainController trainController;
//		
//		public List<TrainModel> trainmodel;
//		
//		@Test 
//		void getAllTrainsTest() {
//			
////			when(trainRepo.findAll()).thenReturn(Stream.of(new TrainModel("555", "12739","ChennaiExpress", "Hyderabad", "Chennai",800,150,"03PM")
////					,new TrainModel("587", "12717","RatnachalExpress", "Vijayawada", "Visakhapatnam",200,120,"05.45AM")).collect(Collectors.toList()));
////			assertEquals(2,trainController.getAllTrains().size());
////			
////		}
//		
////		@Test 
////		void getaddTrainTest() {
////			
////			TrainModel trainmodel = new TrainModel("580", "12777","HowraExpress", "Puri", "Tirupati",1000,300,"05.45PM");
////			
////			
////			when(trainRepo.addTrain(trainmodel)).thenReturn(trainmodel);
////			assertEquals(trainmodel, trainController.addTrain(trainmodel));
////		}
////		
////		@Test 
////		public void getCarWasherByIdTest(){
////			CarWasher carWasher = new CarWasher (5,33,"","","","");
////			Optional<CarWasher> op = Optional.of(carWasher);
////			when(carRepo.findById(2)).thenReturn(op);
////			assertEquals(5,op.get().getCarwasherid());
////		}
////
////		@Test 
////		public void getTrainByNoTest(){
////			TrainModel trainmodel = new TrainModel("580", "12777","HowraExpress", "Puri", "Tirupati",1000,300,"05.45PM");
////			Optional<TrainModel> op = Optional.of(trainmodel);
////			when(trainRepo.findByTrainNo()).thenReturn(op);
////			assertEquals(12777,op.get().getTrainNo());
////		}
////		
//		/*
//		 * @Test public void updateTrainByTrainNo(){ TrainModel trainmodel = new
//		 * TrainModel ("580", "12777","HowraExpress", "Puri",
//		 * "Tirupati",1000,300,"05.45PM" ); when(trainController.update("12777",
//		 * trainmodel)).thenReturn(trainmodel);
//		 * assertEquals(12777,trainmodel.get().getTrainNo()); }
//		 */
////}
