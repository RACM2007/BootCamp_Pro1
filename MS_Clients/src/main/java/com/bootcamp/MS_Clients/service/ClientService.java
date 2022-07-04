package com.bootcamp.MS_Clients.service;

import com.bootcamp.MS_Clients.model.Clients;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Clients> findAll();



    Mono<Clients> save(Clients clients);

    Mono<Clients> update(Clients clients);

    Mono<Clients> logicDelete(String cod);

}
