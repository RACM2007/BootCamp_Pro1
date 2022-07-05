package com.bootcamp.MS_Transactions.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.MS_Transactions.model.Transactions;

@Repository
public interface TransactionRepository  extends ReactiveCrudRepository<Transactions, ObjectId>{

}
