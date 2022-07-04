package com.bootcamp.MS_Savings.service;

import java.util.List;

import com.bootcamp.MS_Savings.model.Accounts_Relationship;

import reactor.core.publisher.Mono;

public interface Accounts_RelationshipService {

	public boolean BusiCond(List<String> codTit);
	public boolean OneSavACond(List<String> codTit);
	public Mono<Accounts_Relationship> save(Accounts_Relationship AR);
	
}
