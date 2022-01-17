package com.example.login.AdminDetails;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.login.AdminDetails.model.UserModel;


@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{

	UserModel findByusername(String username);

}