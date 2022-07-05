package com.bootcamp.MS_Savings.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.bootcamp.MS_Savings.Entity.SavingObj;
import com.bootcamp.MS_Savings.model.Accounts_Relationship;
import com.bootcamp.MS_Savings.model.Savings;
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
	public Mono<Integer> GetTypeAccount(String pro, String Currency, String Number) {
		LogJava.info("Inquiry");
		
		Mono<Savings> Obj1 = savingRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		return Obj1.map( r -> r.getType());
		
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
		
		boolean flg1;
		
		//business condition
		
		if (Sav.getTypeCLi()==1) {
		
			flg1 = PerCond(Sav.getCodTit());
		}else {
			flg1 = BusiCond(Sav.getCodTit());
		}
		
		Savings SavR = Sav.GetSaving();
		
		
		if (flg1) {
			Flux.fromIterable(Sav.GetRelation()).flatMap(R -> 
			Flux.concat(accounts_RelationshipService.save(R))).subscribe();
			
			return savingRepository.save(SavR);
		}else {
			return null;
		}
	}

	private boolean BusiCond(List<String> codTit) {
		
		for(String cc: codTit) {
			//Accounts List
			Flux<Accounts_Relationship> far = accounts_RelationshipService.GetAccountSav(cc);
						
			boolean Bol = far.map(f -> GetTypeAccount(f.getProduct(),f.getCurrency(),f.getNumber()))
				.filter(x -> x.block()==1 || x.block()==2).collect(Collectors.toSet()).block().size()==2;
			
			if (Bol) {
				return false;
			}
		}
		
		return true;
		
		
	}
	
	private boolean PerCond(List<String> codTit) {
		return accounts_RelationshipService.PerCond(codTit);
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
