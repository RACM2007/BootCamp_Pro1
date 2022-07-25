package com.bootcamp.MS_Transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.MS_Transactions.Entity.Pay;
import com.bootcamp.MS_Transactions.Entity.TraBootCoin;
import com.bootcamp.MS_Transactions.Entity.Transfer;
import com.bootcamp.MS_Transactions.model.Transactions;
import com.bootcamp.MS_Transactions.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/transaction")
@CrossOrigin("*")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@GetMapping(value = "/Inquiry/{pro}/{cur}/{num}")
	public Flux<Transactions> Inquiry(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num){
		//Inquiry Transaction
		return transactionService.Inquiry(pro, cur, num);
	}
	
	@GetMapping(value = "/AllTransactions")
	public Flux<Transactions> AllTransactions(){
		//Inquiry Transaction
		return transactionService.AllTransactions();
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
	
	@PostMapping(value="/Transfer")
	public Mono<Transactions> Transfer(@RequestBody Transfer Transfer){
		//Make a credit card consumption
		
		return transactionService.Transfer(Transfer);
	}
	
	@PostMapping(value="/SendPay")
	public Mono<Transactions> Send_Pay(@RequestBody Pay payO){
		//Make a credit card consumption
		
		return transactionService.Send_Pay(payO);
	}
	
	@PostMapping(value="/RecePay")
	public Mono<Transactions> Rece_Pay(@RequestBody Pay payO){
		//Make a credit card consumption
		
		return transactionService.Rece_Pay(payO);
	}
	
	@PostMapping(value="/BuyBootCoin")
	public Mono<Transactions> Buy_BootCoin(@RequestBody TraBootCoin TBC){
		//Make a credit card consumption
		
		return transactionService.Buy_BootCoin(TBC);
	}
	
	@PostMapping(value="/SoldBootCoin")
	public Mono<Transactions> Sold_BootCoin(@RequestBody TraBootCoin TBC){
		//Make a credit card consumption
		
		return transactionService.Sold_BootCoin(TBC);
	}
	
}
