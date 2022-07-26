package com.bootcamp.MS_Clients.service;

import com.bootcamp.MS_Clients.model.NoClients;
import reactor.core.publisher.Mono;

public interface NoClientService {

    public Mono<NoClients> save(NoClients noClients);

}
