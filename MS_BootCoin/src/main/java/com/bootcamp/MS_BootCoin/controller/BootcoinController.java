package com.bootcamp.MS_BootCoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.MS_BootCoin.model.BootCoin;
import com.bootcamp.MS_BootCoin.service.BootcoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value="/bootcoin")
@CrossOrigin("*")
public class BootcoinController {

	@Autowired
    BootcoinService bootcoinService;
	
	@PostMapping(value = "/CurrencyChange/{newchange}")
    public Mono<BootCoin> Currency_Change(@PathVariable("newchange") double Newchange) {
        //List all Commissions
        return bootcoinService.Currency_Change(Newchange);
    }
	
	@GetMapping(value = "/Inquiry")
    public Mono<BootCoin> Inquiry() {
        //List all Commissions
        return bootcoinService.Inquiry();
    }
	
	@GetMapping(value = "/InquiryAll")
    public Flux<BootCoin> InquiryAll() {
        //List all Commissions
        return bootcoinService.InquiryAll();
    }
	
	@PostMapping(value = "/RegistryUser")
    public String Registry_User() {
        //List all Commissions
        return bootcoinService.Registry_User();
    }
	
	@PostMapping(value = "/BuyBootCoin")
    public Mono<BootCoin> Buy_BootCoin() {
        //List all Commissions
        return bootcoinService.Inquiry();
    }
	
	@PostMapping(value = "/SoldBootCoin")
    public Mono<BootCoin> Sold_BootCoin() {
        //List all Commissions
        return bootcoinService.Inquiry();
    }
	
	@GetMapping(value = "/Check_user")
    public Mono<BootCoin> Check_user() {
        //List all Commissions
        return bootcoinService.Inquiry();
    }
	
}
