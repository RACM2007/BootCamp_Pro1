package com.bootcamp.MS_Transactions.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.MS_Transactions.model.Transactions;
import com.bootcamp.MS_Transactions.Entity.Transfer;
import com.bootcamp.MS_Transactions.repository.TransactionRepository;
import com.mongodb.MongoException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	private static Logger LogJava = Logger.getLogger(TransactionServiceImpl.class);
	
	@Override
	public Mono<Transactions> Inquiry(String pro, String Currency, String Number) {
		
		LogJava.info("List all Transaction");
		
		Mono<Transactions> Obj1 = transactionRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				).next();
		
		return Obj1;
	}

	@Override
	public Mono<Transactions> Deposits(String pro, String Currency, String Number, String Codcli, double Amount) {
		
		LogJava.info("Make a Deposit");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(Amount);
		Tra.setCodClient(Codcli);
		Tra.setCurrency(Currency);
		Tra.setDateCreate(new Date());
		Tra.setNumber(Number);
		Tra.setProduct(pro);
		
		Tra= OpeOurEntity(Tra);
		
		
		return RegTra(Tra,1);
	}

	@Override
	public Mono<Transactions> Withdrawal(String pro, String Currency, String Number, String Codcli, double Amount) {
		
		LogJava.info("Make a Withdrawal");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(Amount);
		Tra.setCodClient(Codcli);
		Tra.setCurrency(Currency);
		Tra.setDateCreate(new Date());
		Tra.setNumber(Number);
		Tra.setProduct(pro);
		
		Tra= OpeOurEntity(Tra);
		
		return RegTra(Tra,2);
	}

	@Override
	public Mono<Transactions> CreditPay(String pro, String Currency, String Number, String Codcli, double Amount) {
		
		LogJava.info("Make a Credit Pay");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(Amount);
		Tra.setCodClient(Codcli);
		Tra.setCurrency(Currency);
		Tra.setDateCreate(new Date());
		Tra.setNumber(Number);
		Tra.setProduct(pro);
		
		Tra= OpeOurEntity(Tra);
		
		return RegTra(Tra,3);
	}

	@Override
	public Mono<Transactions> CardPay(String pro, String Currency, String Number, String Codcli, double Amount) {
		
		LogJava.info("Make a Credit Card Pay");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(Amount);
		Tra.setCodClient(Codcli);
		Tra.setCurrency(Currency);
		Tra.setDateCreate(new Date());
		Tra.setNumber(Number);
		Tra.setProduct(pro);
		
		Tra= OpeOurEntity(Tra);
		
		return RegTra(Tra,4);
	}

	@Override
	public Mono<Transactions> ConsumptionCreditCard(String pro, String Currency, String Number, String Codcli, double Amount) {
		
		LogJava.info("Make a ConsumptionCreditCard");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(Amount);
		Tra.setCodClient(Codcli);
		Tra.setCurrency(Currency);
		Tra.setDateCreate(new Date());
		Tra.setNumber(Number);
		Tra.setProduct(pro);
		
		Tra= OpeOurEntity(Tra);
		
		return RegTra(Tra,5);
	}

	private Transactions OpeOurEntity(Transactions Tra) {
		
		String cZero="0";
		
		Tra.setProductD(cZero);
		Tra.setCurrencyD(cZero);
		Tra.setNumberD(cZero);
		Tra.setCodEntity("1");
		
		return Tra;
	}
	
	private Mono<Transactions> RegTra(Transactions Tra, int type){
		Tra.setType(type);
		
		try {
			return transactionRepository.save(Tra);
		}catch (MongoException e) {
			LogJava.error("Error in Save - Mongo - "+e.getMessage());
			return null;
		}
	}

	@Override
	public Flux<Transactions> AllTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public Mono<Transactions> Transfer(Transfer transfer) {
		LogJava.info("Make a ConsumptionCreditCard");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(transfer.getAmount());
		Tra.setCurrency(transfer.getCurrency());
		Tra.setDateCreate(new Date());
		Tra.setNumber(transfer.getNumber());
		Tra.setProduct(transfer.getProduct());
		
		Tra.setProductD(transfer.getProduct());
		Tra.setCurrencyD(transfer.getCurrencyD());
		Tra.setNumberD(transfer.getNumberD());
		Tra.setCodEntity(transfer.getCodEntity());
		
		return RegTra(Tra,5);
	}

	
}
