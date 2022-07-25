package com.bootcamp.MS_Savings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.MS_Savings.Entity.SavingObj;
import com.bootcamp.MS_Savings.model.Debit_Card;
import com.bootcamp.MS_Savings.repository.Debit_CardRepository;

import reactor.core.publisher.Flux;

@Service
public class Debit_CardServiceImpl implements Debit_CardService{

	@Autowired
	private Debit_CardRepository debit_CardRepository;
	
	@Override
	public SavingObj GetPrinSav(String Number) {
		
		Flux<Debit_Card> DC1t = debit_CardRepository.findAll();
		
		System.out.println(DC1t.next().block().toString());
		
		System.out.println("Llegó");
		
		Debit_Card DC1 = debit_CardRepository.findAll().filter(x -> x.getNum_Card().equals(Number)
				).next().block();
		
		SavingObj Obj1 = new SavingObj();
		
		Obj1.setProduct(DC1.getProduct_Prin());
		Obj1.setCurrency(DC1.getCurrency_prin());
		Obj1.setNumber(DC1.getNumber_prin());
		
		return Obj1;
	}

	@Override
	public String GetCodCli(String debitCardNumber) {
		return debit_CardRepository.findAll().filter(x -> x.getNum_Card().equals(debitCardNumber)
				).next().block().getCodcli();
	}

}
