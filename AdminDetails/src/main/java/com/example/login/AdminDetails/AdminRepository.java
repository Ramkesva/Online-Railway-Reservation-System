package com.example.login.AdminDetails;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.login.AdminDetails.model.AdminModel;

@Repository
public interface AdminRepository extends MongoRepository<AdminModel, String>{

	AdminModel findByusername(String username);

}