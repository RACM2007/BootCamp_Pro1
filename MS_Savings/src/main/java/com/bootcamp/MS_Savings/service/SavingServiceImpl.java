package com.bootcamp.MS_Savings.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.bootcamp.MS_Savings.Entity.SavingObj;
import com.bootcamp.MS_Savings.model.Accounts_Relationship;
import com.bootcamp.MS_Savings.model.Savings;
import com.bootcamp.MS_Savings.repository.Accounts_RelationshipRepository;
import com.bootcamp.MS_Savings.repository.SavingRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingServiceImpl implements SavingService{

	@Autowired
	private SavingRepository savingRepository;
	
	@Autowired
	private Accounts_RelationshipService accounts_RelationshipService;
	
	private static Logger LogJava = Logger.getLogger(SavingServiceImpl.class);

	@Override
	public Flux<Savings> AllSavings() {
		LogJava.info("List all Savings Account");
		return savingRepository.findAll();
	}


	@Override
	public Mono<Double> Inquiry(String pro, String Currency, String Number) {
		LogJava.info("Inquiry");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		return Obj1.map( r -> r.getAmount());
		
	}

	@Override
	public Mono<Savings> AmountUpdate(String pro, String Currency, String Number,double NewAmou) {
		LogJava.info("AmountUpdate");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		
		Savings Sav = Obj1.block();
		
		Sav.setAmount(NewAmou);
		
		return savingRepository.save(Sav);
	}


	@Override
	public Mono<Savings> Open(SavingObj Sav) {
		LogJava.info("Open Saving");
		
		//One Saving account condition
		boolean flg1 = OneSavACond(Sav.getCodTit());
		
		//business condition
		boolean flg2 = BusiCond(Sav.getCodTit());
		
		Savings SavR = Sav.GetSaving();
		
		
		if (flg1 && flg2) {
			Flux.fromIterable(Sav.GetRelation()).flatMap(R -> 
			Flux.concat(accounts_RelationshipService.save(R))).subscribe();
			
			return savingRepository.save(SavR);
		}else {
			return null;
		}
	}

	private boolean BusiCond(List<String> codTit) {
		return accounts_RelationshipService.BusiCond(codTit);
	}


	private boolean OneSavACond(List<String> codTit) {
		return accounts_RelationshipService.OneSavACond(codTit);
	}


	@Override
	public Mono<Savings> Close(String pro, String Currency, String Number) {
		LogJava.info("Close Saving");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		
		Savings Sav = Obj1.block();
		
		Sav.setFlgActive(0);
		
		return savingRepository.save(Sav);
	}


	@Override
	public Mono<Void> Delete(String pro, String Currency, String Number) {
		LogJava.info("Delete Saving");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		
		Savings Sav = Obj1.block();
		
		return savingRepository.delete(Sav);
	}
	



	
		

}
