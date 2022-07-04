package com.bootcamp.MS_Clients.repository;

import com.bootcamp.MS_Clients.model.Clients;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Clients, ObjectId> {
}
