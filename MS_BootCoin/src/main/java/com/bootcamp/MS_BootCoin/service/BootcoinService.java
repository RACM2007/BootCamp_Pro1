package com.bootcamp.MS_BootCoin.service;

import com.bootcamp.MS_BootCoin.model.BootCoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinService {

	Mono<BootCoin> Currency_Change(double BC);

	Mono<BootCoin> Inquiry();

	Flux<BootCoin> InquiryAll();

	String Registry_User();

}
