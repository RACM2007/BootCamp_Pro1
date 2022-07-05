package com.bootcamp.MS_Transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.MS_Transactions.model.Transactions;
import com.bootcamp.MS_Transactions.service.TransactionService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/transaction")
@CrossOrigin("*")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@GetMapping(value = "/Inquiry/{pro}/{cur}/{num}")
	public Mono<Transactions> Inquiry(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num){
		//Inquiry Transaction
		return transactionService.Inquiry(pro, cur, num);
	}
	
	@PostMapping(value="/Deposits/{pro}/{cur}/{num}/{codcli}/{newamou}")
	public Mono<Transactions> Deposits(@PathVariable("pro")String pro, 
			@PathVariable("cur")String Currency, 
			@PathVariable("num") String Number,
			@PathVariable("codcli") String Codcli,
			@PathVariable("newamou") double Amount){
		//Make a Deposit in a Saving Account
		
		return transactionService.Deposits(pro, Currency, Number,Codcli, Amount);
	}
	
	@PostMapping(value="/Withdrawal/{pro}/{cur}/{num}/{codcli}/{newamou}")
	public Mono<Transactions> Withdrawal(@PathVariable("pro")String pro, 
			@PathVariable("cur")String Currency, 
			@PathVariable("num") String Number,
			@PathVariable("codcli") String Codcli,
			@PathVariable("newamou") double Amount){
		//Make a Withdrawal in a Saving Account
		
		return transactionService.Withdrawal(pro, Currency, Number,Codcli, Amount);
	}
	
	@PostMapping(value="/CreditPay/{pro}/{cur}//{num}/{codcli}/{newamou}")
	public Mono<Transactions> CreditPay(@PathVariable("pro")String pro, 
			@PathVariable("cur")String Currency, 
			@PathVariable("num") String Number,
			@PathVariable("codcli") String Codcli,
			@PathVariable("newamou") double Amount){
		//Make a Credit Pay
		
		return transactionService.CreditPay(pro, Currency, Number,Codcli, Amount);
	}
	
	@PostMapping(value="/CardPay/{pro}/{cur}//{num}/{codcli}/{newamou}")
	public Mono<Transactions> CardPay(@PathVariable("pro")String pro, 
			@PathVariable("cur")String Currency, 
			@PathVariable("num") String Number,
			@PathVariable("codcli") String Codcli,
			@PathVariable("newamou") double Amount){
		//Make a CreditCard Pay
		return transactionService.CardPay(pro, Currency, Number,Codcli, Amount);
	}
	
	@PostMapping(value="/ConsumptionCreditCard/{pro}/{cur}//{num}/{codcli}/{newamou}")
	public Mono<Transactions> ConsumptionCreditCard(@PathVariable("pro")String pro, 
			@PathVariable("cur")String Currency, 
			@PathVariable("num") String Number,
			@PathVariable("codcli") String Codcli,
			@PathVariable("newamou") double Amount){
		//Make a credit card consumption
		
		return transactionService.ConsumptionCreditCard(pro, Currency, Number,Codcli, Amount);
	}
	
}
