package com.bootcamp.MS_Savings.service;

import java.util.List;

import com.bootcamp.MS_Savings.model.Accounts_Relationship;
import com.bootcamp.MS_Savings.model.Savings;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Accounts_RelationshipService {

	public boolean PerCond(List<String> codTit);
	
	public Flux<Accounts_Relationship> GetAccountSav(String codcli);
	
	public List<Accounts_Relationship> GetAccountSavList(String codcli);
	
	public Mono<Accounts_Relationship> save(Accounts_Relationship AR);
	
}
