package com.bootcamp.MS_Credits.service;

import com.bootcamp.MS_Credits.interfaces.SetAmount;
import com.bootcamp.MS_Credits.model.Credits;
import com.bootcamp.MS_Credits.model.SumAmmount;
import com.bootcamp.MS_Credits.repository.CreditRepository;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    private static  Logger LogJava = Logger.getLogger(CreditServiceImpl.class);

    @Override
    public Flux<Credits> findAll() {
        LogJava.info("List all Credits");
        return creditRepository.findAll();
    }

    private Mono<Credits> RegCred(Credits Cre, Double Amount){

        SetAmount SA = (C, a) -> C.setAmount(a);

        SA.Set_Amount(Cre, Amount);

        return save(Cre);

    }

    @Override
    public Mono<Credits> save(Credits credits) {
        try{
            LogJava.info("Save");
            return creditRepository.save(credits);
        }catch (MongoException e){
            LogJava.error("Error in Save - Mongo - "+e.getMessage());
            return null;
        }
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
    public Mono<Credits> getCreditbyCodCli(String cod) {
        LogJava.info("Get Credits by CodCli");

        Mono<Credits> Obj1 = creditRepository.findAll().filter(x -> x.getCodClient().equals(cod)
        ).next();

        return Obj1;
    }

    @Override
    public Map<String, Object> getReportbyProduct(String pro) {

        LogJava.info("Generate Report");

        //Credits Account List
        Map<String, Object> response = new HashMap<String, Object>();

        Flux<Credits> PagReport = creditRepository.findAll().filter(x -> x.getProduct().equals(pro));

        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC,"DateCreate"));
        MatchOperation comparisonOperatorsA = Aggregation.match(Criteria.where("Product").is(pro));
        Aggregation aggregationAll = Aggregation.newAggregation(sortOperation,comparisonOperatorsA);

        AggregationResults<Credits> resultsAll =
                mongoTemplate.aggregate(aggregationAll,"Credits", Credits.class);

        //Credits List
        response.put("Credits_Account_List", resultsAll.getMappedResults());

        //Total Elements
        response.put("Total_Savings_Account", PagReport.count().block());

        //Ammount Sum
        GroupOperation groupOperation = Aggregation.group().sum("Amount").as("totalAmount");
        MatchOperation comparisonOperators = Aggregation.match(Criteria.where("FlgActive").is(1));

        TypedAggregation<Credits> aggregation = Aggregation.newAggregation(
                Credits.class,
                comparisonOperators,
                comparisonOperatorsA,
                groupOperation
        );

        AggregationResults<SumAmmount> results =
                mongoTemplate.aggregate(aggregation, SumAmmount.class);

        response.put("Total_Sum_Amount", results.getMappedResults());

        //Ammount Average
        GroupOperation groupOperation2 = Aggregation.group().avg("Amount").as("totalAmount");
        MatchOperation comparisonOperators2 = Aggregation.match(Criteria.where("FlgActive").is(1));

        TypedAggregation<Credits> aggregation2 = Aggregation.newAggregation(
                Credits.class,
                comparisonOperators2,
                comparisonOperatorsA,
                groupOperation2
        );

        AggregationResults<SumAmmount> results2 =
                mongoTemplate.aggregate(aggregation2, SumAmmount.class);

        response.put("Average_Amount", results2.getMappedResults());

        //Rate Average
        GroupOperation groupOperation3 = Aggregation.group().avg("Rate").as("totalAmount");

        TypedAggregation<Credits> aggregation3 = Aggregation.newAggregation(
                Credits.class,
                groupOperation3
        );

        AggregationResults<SumAmmount> results3 =
                mongoTemplate.aggregate(aggregation3, SumAmmount.class);

        response.put("Average_Rate", results3.getMappedResults());

        //Last Saving
        response.put("Saving_Last", resultsAll.getMappedResults().get(resultsAll.getMappedResults().size()-1));

        return response;
    }

    @Override
    public Mono<Credits> getReport() {

        LogJava.info("Get Report");

        Mono<Credits> Obj1 = creditRepository.findAll().next();

        Credits Cred = Obj1.block();

        Cred.getAmount();

        return Obj1;
    }

    @Override
    public Mono<Credits> getDebt(String cod) {
        LogJava.info("Generate Debt");

        Mono<Credits> Obj1 = creditRepository.findAll().filter(x -> x.getCodClient().equals(cod)
                && x.getFlgExpiration() == 0
        ).next();

        return Obj1;

    }

    @Override
    public Mono<Credits> updateBalance(String pro, String currency, String number, double NewAmou) {
        LogJava.info("Update Balance");

        Mono<Credits> Obj1 = creditRepository.findAll().filter(x -> x.getProduct().equals(pro)
                && x.getCurrency().equals(currency)
                && x.getNumber().equals(number)
        ).next();

        Credits Cred = Obj1.block();

        return RegCred(Cred, NewAmou);

        /*Cred.setAmount(NewAmou);

        return creditRepository.save(Cred);*/
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
