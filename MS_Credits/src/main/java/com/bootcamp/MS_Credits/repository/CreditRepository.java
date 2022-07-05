package com.bootcamp.MS_Credits.repository;

import com.bootcamp.MS_Credits.model.Credits;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends ReactiveCrudRepository<Credits, ObjectId> {
}
