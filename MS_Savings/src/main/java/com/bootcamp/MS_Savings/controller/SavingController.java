package com.bootcamp.MS_Savings.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.MS_Savings.service.SavingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.bootcamp.MS_Savings.Entity.SavingObj;
import com.bootcamp.MS_Savings.model.Savings;


@RestController
@RequestMapping(value="/saving")
@CrossOrigin("*")
public class SavingController {

	@Autowired
	SavingService savingService;
	
	public static final String SAV_SERVICE = "savingService";
	
	@GetMapping(value = "/Get_Report")
	public Map<String,Object> GetReport() {
		//Get a Resume about the savings
		return savingService.SavReport();
	}
	
	@GetMapping(value = "/Savings_byCodcli/{codcli}")
	public Flux<Savings> Savings_byCodcli(@PathVariable("codcli") String codcli) {
		//List of savings by Codcli
		return savingService.Savings_byCodcli(codcli);
	}
	
	@GetMapping(value = "/AllSavings")
	public Flux<Savings> AllSavings() {
		//list all savings accounts
		return savingService.AllSavings();
	}
	
	@GetMapping(value = "/Inquiry/{pro}/{cur}/{num}")
	public Mono<Double> Inquiry(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num) {
		//Balance inquiry
		return savingService.Inquiry(pro, cur, num);
	}
	
	@GetMapping(value = "/InquiryDebitCard/{num}")
	public Mono<Double> InquiryDebitCard(@PathVariable("num") String num) {
		//Balance inquiry
		return savingService.InquiryDebitCard(num);
	}
	
	@PutMapping(value = "/AmountUpdate/{pro}/{cur}/{num}/{newamou}")
	@CircuitBreaker(name = SAV_SERVICE,fallbackMethod = "getInquiryZero")
	public Mono<Savings> AmountUpdate(@PathVariable("pro")String pro, 
			@PathVariable("cur")String Currency, 
			@PathVariable("num") String Number,
			@PathVariable("newamou") double NewAmou) throws Exception{
		//Amount update of a Saving Account 
		return savingService.AmountUpdate(pro, Currency, Number, NewAmou);
	}
	
	public Mono<Savings> getInquiryZero(Exception e){
		Savings Sav = new Savings();
		
		Sav.setAmount(-10);
		
		Mono<Savings> SavReturn = Mono.just(Sav);
		
		return SavReturn;
	}
	
	@PostMapping(value="/Open")
    public Mono<Savings> Open(@RequestBody SavingObj Sav){
		//Open a Saving Account 
        return savingService.Open(Sav);
    }
	
	@DeleteMapping(value="/Close/{pro}/{cur}/{num}")
    public Mono<Savings> Close(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num){
		//Close a Saving Account 
        return savingService.Close(pro, cur, num);
    }
	
	@DeleteMapping(value="/Delete/{pro}/{cur}/{num}")
    public Mono<Void> Delete(@PathVariable("pro") String pro, @PathVariable("cur") String cur, @PathVariable("num") String num){
		//Delete a Saving Account 
        return savingService.Delete(pro, cur, num);
    }
}
