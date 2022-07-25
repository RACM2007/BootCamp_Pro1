package com.bootcamp.MS_Clients.repository;

import com.bootcamp.MS_Clients.model.NoClients;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface NoClientRepository extends ReactiveCrudRepository<NoClients, ObjectId> {
}
