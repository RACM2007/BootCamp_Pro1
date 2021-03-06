package com.bootcamp.MS_Transactions.service;

import com.bootcamp.MS_Transactions.Entity.Pay;
import com.bootcamp.MS_Transactions.Entity.TraBootCoin;
import com.bootcamp.MS_Transactions.Entity.Transfer;
import com.bootcamp.MS_Transactions.model.Transactions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

	//Methods Declaration
	
	public Flux<Transactions> Inquiry(String pro, String Currency, String Number);
	public Flux<Transactions> AllTransactions();
	public Mono<Transactions> Deposits(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> Withdrawal(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> CreditPay(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> CardPay(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> ConsumptionCreditCard(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> Transfer(Transfer transfer);
	
	public Mono<Transactions> RegTra(Transactions Tra, int type);
	public Mono<Transactions> Send_Pay(Pay payO);
	public Mono<Transactions> Rece_Pay(Pay payO);
	
	public Mono<Transactions> Buy_BootCoin(TraBootCoin tBC);
	public Mono<Transactions> Sold_BootCoin(TraBootCoin tBC);
	
}
