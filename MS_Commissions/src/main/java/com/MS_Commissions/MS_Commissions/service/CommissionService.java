package com.MS_Commissions.MS_Commissions.service;

import java.util.Map;

import com.MS_Commissions.MS_Commissions.model.Commissions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommissionService {

    public Flux<Commissions> findAll();

    public Mono<Commissions> save(Commissions commissions);

    public Map<String,Object> getReport();

}
