package com.example.micro.TrainDetails;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.micro.TrainDetails.model.TrainModel;

@Repository
public interface TrainRepository extends MongoRepository<TrainModel, String> {

	TrainModel findByTrainFromAndTrainTo(String trainFrom, String trainTo);

	List<TrainModel> findByTrainName(String trainName);

	TrainModel findByTrainNo(String trainNo);

	List<TrainModel> findByTrainFrom(String trainFrom);

	List<TrainModel> findByTrainTo(String trainTo);

	
}