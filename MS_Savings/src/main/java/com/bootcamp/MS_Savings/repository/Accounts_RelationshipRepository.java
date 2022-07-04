package com.bootcamp.MS_Savings.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.MS_Savings.model.Accounts_Relationship;

@Repository
public interface Accounts_RelationshipRepository extends ReactiveCrudRepository<Accounts_Relationship, ObjectId>{

}
