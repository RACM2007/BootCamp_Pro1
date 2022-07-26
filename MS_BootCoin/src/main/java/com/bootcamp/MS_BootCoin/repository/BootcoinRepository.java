package com.bootcamp.MS_BootCoin.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.MS_BootCoin.model.BootCoin;

@Repository
public interface BootcoinRepository extends ReactiveCrudRepository<BootCoin, ObjectId> {

}
