package com.bootcamp.MS_Clients.controller;

import com.bootcamp.MS_Clients.model.NoClients;
import com.bootcamp.MS_Clients.service.ClientService;
import com.bootcamp.MS_Clients.service.NoClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public class NoClientController {

    @Autowired
    NoClientService noClientService;

    @PostMapping(value = "/NewNoClient")
    public Mono<NoClients> save(@RequestBody NoClients noClients){
        //Save No Client
        return noClientService.save(noClients);
    }

}
