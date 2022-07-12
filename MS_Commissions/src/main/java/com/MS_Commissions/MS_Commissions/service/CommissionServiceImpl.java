package com.MS_Commissions.MS_Commissions.service;

import com.MS_Commissions.MS_Commissions.model.Commissions;
import com.MS_Commissions.MS_Commissions.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.apache.log4j.Logger;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    private static Logger LogJava = Logger.getLogger(CommissionServiceImpl.class);

    @Override
    public Flux<Commissions> findAll() {
        LogJava.info("List all Commissions");
        return commissionRepository.findAll();
    }

    @Override
    public Mono<Commissions> save(Commissions commissions) {
        LogJava.info("Save");
        return commissionRepository.save(commissions);
    }

    @Override
    public Mono<Commissions> getReport() {
        return null;
    }
}
