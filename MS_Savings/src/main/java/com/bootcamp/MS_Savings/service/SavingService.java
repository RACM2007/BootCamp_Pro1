package com.bootcamp.MS_Savings.service;


import java.util.Map;

import com.bootcamp.MS_Savings.Entity.SavingObj;
import com.bootcamp.MS_Savings.model.Savings;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingService {

	//Methods Declaration
	
	public Mono<Savings> Open (SavingObj Sav);
	
	public Mono<Savings> Close (String pro, String Currency, String Number);
	
	public Mono<Void> Delete (String pro, String Currency, String Number);
	
	public Flux<Savings> AllSavings();
	
	public Flux<Savings> Savings_byCodcli(String codcli);
	
	public Mono<Double> Inquiry(String pro, String Currency, String Number);
	
	public Mono<Double> InquiryDebitCard(String Number);
	
	public Mono<Integer> GetTypeAccount(String pro, String Currency, String Number);
	
	public Mono<Savings> AmountUpdate(String pro, String Currency, String Number,double NewAmou) throws Exception;
	
	public Mono<Savings> GetAccountOrden(String DebitCardNumber, double Amount);
	
	public Map<String,Object> SavReport();
	
	public Map<String,Object> SavReportByProduct(String pro);
	
}