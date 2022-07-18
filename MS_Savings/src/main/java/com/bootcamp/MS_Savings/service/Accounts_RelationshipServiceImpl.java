package com.bootcamp.MS_Savings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.bootcamp.MS_Savings.model.Accounts_Relationship;
import com.bootcamp.MS_Savings.model.Savings;
import com.bootcamp.MS_Savings.repository.Accounts_RelationshipRepository;
import com.bootcamp.MS_Savings.repository.SavingRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Accounts_RelationshipServiceImpl  implements Accounts_RelationshipService{

	@Autowired
	private Accounts_RelationshipRepository accounts_RelationshipRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
		
	@Override
	public boolean PerCond(List<String> codTit) {
		for (String codcli: codTit) {
			
			Mono<Accounts_Relationship> Obj1 = accounts_RelationshipRepository.findAll().filter(x -> x.getCodClient().equals(codcli)
					).next();
			
			if (Obj1 != null) {
				return  true;
			}
		}
		
		return false;
	}
	
	@Override
	public Mono<Accounts_Relationship> save(Accounts_Relationship AR) {
		return accounts_RelationshipRepository.save(AR);
		
	}

	@Override
	public Flux<Accounts_Relationship> GetAccountSav(String codcli) {
		Flux<Accounts_Relationship> Obj1 = accounts_RelationshipRepository.findAll().filter(x -> x.getCodClient().equals(codcli)
				);
		
		return Obj1;
	}

	@Override
	public List<Accounts_Relationship> GetAccountSavList(String codcli) {
		MatchOperation comparisonOperators = Aggregation.match(Criteria.where("CodClient").is(codcli));
		Aggregation aggregationAll = Aggregation.newAggregation(comparisonOperators);
		
		AggregationResults<Accounts_Relationship> resultsAll =
				mongoTemplate.aggregate(aggregationAll,"Accounts_Relationship", Accounts_Relationship.class);
		
		return resultsAll.getMappedResults();
		
	}

}
