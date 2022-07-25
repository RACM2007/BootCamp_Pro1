package com.bootcamp.MS_Transactions.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.MS_Transactions.model.Transactions;
import com.bootcamp.MS_Transactions.Entity.Pay;
import com.bootcamp.MS_Transactions.Entity.TraBootCoin;
import com.bootcamp.MS_Transactions.Entity.Transfer;
import com.bootcamp.MS_Transactions.interfaces.SetType;
import com.bootcamp.MS_Transactions.repository.TransactionRepository;
import com.mongodb.MongoException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	private static Logger LogJava = Logger.getLogger(TransactionServiceImpl.class);
	
	private SetType SF;
	
	@Override
	public Flux<Transactions> Inquiry(String pro, String Currency, String Number) {
		
		LogJava.info("List all Transaction");
		
		Flux<Transactions> Obj1 = transactionRepository.findAll().filter(x -> x.getProduct().equals(pro)
				&& x.getCurrency().equals(Currency)
				&& x.getNumber().equals(Number)
				);
		
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
	
	@Override
	public Mono<Transactions> RegTra(Transactions Tra, int type){
		
		SetType SF = (T , y) -> T.setType(y);
		
		SF.Set_Type(Tra, type);
		
		return Save(Tra);
		
	}

	private Mono<Transactions> Save (Transactions Tra){
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
	
	public Mono<Transactions> Transfer(Transfer transfer, String numDebitCard) {
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
		
		Tra.setCardNumber(numDebitCard);
		
		return RegTra(Tra,4);
	}

	@Override
	public Mono<Transactions> Send_Pay(Pay payO) {
		LogJava.info("Send Pay");
		
		Transactions Tra = new Transactions();
		Tra.setAmount(payO.getAmount());
		Tra.setCodClient(payO.getCodClient());
		Tra.setCodNoClient(payO.getCodNoClient());
		Tra.setPhone_Number(payO.getPhone_Number());
		Tra.setDateCreate(new Date());
		
		return RegTra(Tra,6);
		
	}

	@Override
	public Mono<Transactions> Rece_Pay(Pay payO) {
		LogJava.info("Send Pay");
		
		Transactions Tra = new Transactions();
		Tra.setAmount(payO.getAmount());
		Tra.setCodClient(payO.getCodClient());
		Tra.setCodNoClient(payO.getCodNoClient());
		Tra.setPhone_Number(payO.getPhone_Number());
		Tra.setDateCreate(new Date());
		
		return RegTra(Tra,7);
		
	}

	@Override
	public Mono<Transactions> Buy_BootCoin(TraBootCoin tBC) {
		LogJava.info("Buy BootCoin");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(tBC.getAmount());
		Tra.setCodClient(tBC.getCodClient());
		Tra.setCurrency("03");
		Tra.setDateCreate(new Date());
		Tra.setNumber(tBC.getNumber());
		Tra.setProduct(tBC.getProduct());
		
		Tra= OpeOurEntity(Tra);
		
		return RegTra(Tra,8);
	}

	@Override
	public Mono<Transactions> Sold_BootCoin(TraBootCoin tBC) {
		LogJava.info("Sold BootCoin");
		
		Transactions Tra = new Transactions();
		
		Tra.setAmount(tBC.getAmount());
		Tra.setCodClient(tBC.getCodClient());
		Tra.setCurrency("03");
		Tra.setDateCreate(new Date());
		Tra.setNumber(tBC.getNumber());
		Tra.setProduct(tBC.getProduct());
		
		Tra= OpeOurEntity(Tra);
		
		return RegTra(Tra,9);
		
	}

	
}
