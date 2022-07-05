package com.bootcamp.MS_Transactions.service;

import com.bootcamp.MS_Transactions.model.Transactions;

import reactor.core.publisher.Mono;

public interface TransactionService {

	//Methods Declaration
	
	public Mono<Transactions> Inquiry(String pro, String Currency, String Number);
	public Mono<Transactions> Deposits(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> Withdrawal(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> CreditPay(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> CardPay(String pro, String Currency, String Number, String Codcli, double Amount);
	public Mono<Transactions> ConsumptionCreditCard(String pro, String Currency, String Number, String Codcli, double Amount);
	
}