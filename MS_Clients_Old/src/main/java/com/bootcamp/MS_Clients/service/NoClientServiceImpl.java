package com.bootcamp.MS_Clients.service;

import com.bootcamp.MS_Clients.model.NoClients;
import com.bootcamp.MS_Clients.repository.NoClientRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class NoClientServiceImpl implements NoClientService {

    @Autowired
    private NoClientRepository noClientRepository;

    private static Logger LogJava = Logger.getLogger(NoClientServiceImpl.class);

    @Override
    public Mono<NoClients> save(NoClients noClients) {
        LogJava.info("Save No Clients");
        return noClientRepository.save(noClients);
    }
}
