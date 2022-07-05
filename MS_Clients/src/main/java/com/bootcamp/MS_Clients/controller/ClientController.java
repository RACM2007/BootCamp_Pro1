package com.bootcamp.MS_Clients.controller;

import com.bootcamp.MS_Clients.model.Clients;
import com.bootcamp.MS_Clients.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/AllClients")
    public Flux<Clients> AllClients() {
        return clientService.findAll();
    }

    @GetMapping(value = "/GetClient/{cod}")
    public Mono<Clients> Inquiry(@PathVariable("cod") String cod) {
        return clientService.getClient(cod);
    }

    @PostMapping(value = "/NewClient")
    public Mono<Clients> save(@RequestBody Clients clients){
        return clientService.save(clients);
    }

    @PutMapping(value = "/UpdateClient")
    public Mono<Clients> update(@RequestBody Clients clients){
        return clientService.update(clients);
    }

    @PutMapping(value = "/LogicDelete/{cod}")
    public Mono<Clients> logicDelete(@PathVariable("cod")String cod){
        return clientService.logicDelete(cod);
    }

}
