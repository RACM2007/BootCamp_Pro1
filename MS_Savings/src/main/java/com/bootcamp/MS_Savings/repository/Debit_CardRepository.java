package com.bootcamp.MS_Savings.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.MS_Savings.model.Debit_Card;
import com.bootcamp.MS_Savings.model.Savings;

@Repository
public interface Debit_CardRepository extends ReactiveCrudRepository<Debit_Card, ObjectId>{

}
