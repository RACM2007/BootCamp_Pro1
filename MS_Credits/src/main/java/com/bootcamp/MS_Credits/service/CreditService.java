package com.bootcamp.MS_Credits.service;

import com.bootcamp.MS_Credits.model.Credits;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CreditService {

    public Flux<Credits> findAll();

    public Mono<Credits> save(Credits credits);

    public Mono<Double> Inquiry(String pro, String currency, String number);

    public Mono<Credits> getCreditbyCodCli(String cod);

    public Map<String,Object> getReportbyProduct(String cod);

    public Mono<Credits> getReport();

    public Mono<Credits> getDebt(String cod);

    public Mono<Credits> updateBalance(String pro, String currency, String number, double NewAmou);

    public Mono<Void> delete(String pro, String currency, String number);

}
