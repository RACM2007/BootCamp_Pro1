package com.bootcamp.MS_Savings.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
