package com.bootcamp.MS_Credits.service;

import com.bootcamp.MS_Credits.model.Credits;
import com.bootcamp.MS_Credits.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.apache.log4j.Logger;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    private static  Logger LogJava = Logger.getLogger(CreditServiceImpl.class);

    @Override
    public Flux<Credits> findAll() {
        LogJava.info("List all Credits");
        return creditRepository.findAll();
    }

    @Override
    public Mono<Credits> save(Credits credits) {
        LogJava.info("Save");
        return creditRepository.save(credits);
    }

    @Override
    public Mono<Double> Inquiry(String pro, String currency, String number) {
        LogJava.info("Inquiry");

        Mono<Credits> Obj1 = creditRepository.findAll().filter(x -> x.getProduct().equals(pro)
                && x.getCurrency().equals(currency)
                && x.getNumber().equals(number)
        ).next();

        return Obj1.map( r -> r.getAmount());
    }

    @Override
    public Mono<Credits> updateBalance(String pro, String currency, String number, double NewAmou) {
        LogJava.info("Update Balance");

        Mono<Credits> Obj1 = creditRepository.findAll().filter(x -> x.getProduct().equals(pro)
                && x.getCurrency().equals(currency)
                && x.getNumber().equals(number)
        ).next();

        Credits Cred = Obj1.block();

        Cred.setAmount(NewAmou);

        return creditRepository.save(Cred);
    }

    @Override
    public Mono<Void> delete(String pro, String currency, String number) {
        LogJava.info("Delete Credit");

        Mono<Credits> Obj1 = creditRepository.findAll().filter(x -> x.getProduct().equals(pro)
                && x.getCurrency().equals(currency)
                && x.getNumber().equals(number)
        ).next();

        Credits Cred = Obj1.block();

        return creditRepository.delete(Cred);
    }
}
