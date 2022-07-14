package com.bootcamp.MS_Savings.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.MS_Savings.model.Savings;

import reactor.core.publisher.Flux;

@Repository
public interface SavingRepository extends ReactiveCrudRepository<Savings, ObjectId>{
	
	

}
