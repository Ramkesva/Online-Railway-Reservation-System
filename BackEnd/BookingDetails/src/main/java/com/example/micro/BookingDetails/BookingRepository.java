package com.example.micro.BookingDetails;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.micro.BookingDetails.model.BookingModel;


public interface BookingRepository extends MongoRepository<BookingModel, String> {

	List<BookingModel> findByUserId(String userid);

}
