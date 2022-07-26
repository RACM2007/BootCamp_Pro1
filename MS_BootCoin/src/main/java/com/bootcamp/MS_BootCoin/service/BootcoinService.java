package com.bootcamp.MS_BootCoin.service;

import com.bootcamp.MS_BootCoin.Entity.Clients;
import com.bootcamp.MS_BootCoin.Entity.NoClients;
import com.bootcamp.MS_BootCoin.Entity.TraBootCoin;
import com.bootcamp.MS_BootCoin.model.BootCoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinService {

	Mono<BootCoin> Currency_Change(double BC);

	Mono<BootCoin> Inquiry();

	Flux<BootCoin> InquiryAll();

	String RegistryUser_Cli(Clients cli);
	
	String RegistryUser_NoCli(NoClients Ncli);

	String Buy_BootCoin(TraBootCoin tBC);

	String Sold_BootCoin(TraBootCoin tBC);

}
