package com.bootcamp.MS_Clients.service;

import com.bootcamp.MS_Clients.model.Clients;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    public Flux<Clients> findAll();

    public Mono<Clients> getClient(String cod);

    public Mono<Clients> save(Clients clients);

    public Mono<Clients> update(Clients clients);

    public Mono<Clients> logicDelete(String cod);

}
