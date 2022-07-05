package com.bootcamp.MS_Clients.service;

import com.bootcamp.MS_Clients.model.Clients;
import com.bootcamp.MS_Clients.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.apache.log4j.Logger;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private static  Logger LogJava = Logger.getLogger(ClientServiceImpl.class);

    @Override
    public Flux<Clients> findAll() {
        LogJava.info("List all Clients");
        return clientRepository.findAll();
    }

    @Override
    public Mono<Clients> getClient(String cod) {
        LogJava.info("Get Clients");

        Mono<Clients> Obj1 = clientRepository.findAll().filter(x -> x.getCodClient().equals(cod)
        ).next();

        Clients Cli = Obj1.block();

        return clientRepository.findById(Cli.getId());
    }

    @Override
    public Mono<Clients> save(Clients clients) {
        LogJava.info("Save");
        return clientRepository.save(clients);
    }

    @Override
    public Mono<Clients> update(Clients clients) {
        LogJava.info("Update");
        Mono<Clients> Obj1 = clientRepository.findAll().filter(x -> x.getCodClient().equals(clients.getCodClient())
        ).next();

        Clients Cli = Obj1.block();

        Cli.setName(clients.getName());
        Cli.setAddress(clients.getAddress());
        Cli.setDocument(clients.getDocument());
        Cli.setFlgActive(clients.getFlgActive());

        return clientRepository.save(Cli);
    }

    @Override
    public Mono<Clients> logicDelete(String cod) {

        LogJava.info("Logic Delete");

        Mono<Clients> Obj1 = clientRepository.findAll().filter(x -> x.getCodClient().equals(cod)).next();

        Clients Cli = Obj1.block();

        Cli.setFlgActive(0);

        return clientRepository.save(Cli);
    }
}
