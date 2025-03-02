package com.example.login.AdminDetails;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.login.AdminDetails.model.TrainModel;


@Repository
public interface TrainRepository extends MongoRepository<TrainModel, String> {

	TrainModel findByTrainFromAndTrainTo(String trainFrom, String trainTo);

	TrainModel findByTrainName(String trainName);

	Optional<TrainModel> findByTrainNo(String trainNo);

	
}