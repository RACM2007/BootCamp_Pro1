package com.bootcamp.MS_BootCoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.MS_BootCoin.Entity.Clients;
import com.bootcamp.MS_BootCoin.Entity.NoClients;
import com.bootcamp.MS_BootCoin.Entity.TraBootCoin;
import com.bootcamp.MS_BootCoin.Entity.UserBC;
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
	
	@PostMapping(value = "/CurrencyChange/{newvalue}")
    public Mono<BootCoin> Currency_Change(@PathVariable("newvalue") double NewValue) {
        //List all CurrencyChange
        return bootcoinService.Currency_Change(NewValue);
    }
	
	@GetMapping(value = "/Inquiry")
    public Mono<BootCoin> Inquiry() {
        //List all Inquiry
        return bootcoinService.Inquiry();
    }
	
	@GetMapping(value = "/InquiryAll")
    public Flux<BootCoin> InquiryAll() {
        //List all InquiryAll
        return bootcoinService.InquiryAll();
    }
	
	@PostMapping(value = "/RegistryUserC/{value}")
    public String Registry_User(@PathVariable("value") int isValue, @RequestBody UserBC uBC) {

        //Registry User, 1 = Client, 2 = No Client
		
		if (isValue==1) {
			Clients cli = uBC.toClient();
			return bootcoinService.RegistryUser_Cli(cli);
		}else {
			NoClients Ncli = uBC.toNoClient();
			return bootcoinService.RegistryUser_NoCli(Ncli);
		}
		
    }
	
	@PostMapping(value = "/BuyBootCoin")
    public String Buy_BootCoin(@RequestBody TraBootCoin TBC) {
        //Buy BuyBootCoin
        return bootcoinService.Buy_BootCoin(TBC);
    }
	
	@PostMapping(value = "/SoldBootCoin")
    public String Sold_BootCoin(@RequestBody TraBootCoin TBC) {
        //Sold BuyBootCoin
        return bootcoinService.Sold_BootCoin(TBC);
    }
	
}
