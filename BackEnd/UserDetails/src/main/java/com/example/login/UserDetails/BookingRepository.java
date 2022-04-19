package com.example.login.UserDetails;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.login.UserDetails.model.BookingModel;


public interface BookingRepository extends MongoRepository<BookingModel, String> {

	List<BookingModel> findByUserId(String userid);

}
