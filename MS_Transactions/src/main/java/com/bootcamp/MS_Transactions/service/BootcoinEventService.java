package com.bootcamp.MS_Transactions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bootcamp.MS_Transactions.Entity.TraBootCoin;
import com.bootcamp.MS_Transactions.events.BootCoinCreatedEvent;
import com.bootcamp.MS_Transactions.events.Event;
import com.bootcamp.MS_Transactions.model.Transactions;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BootcoinEventService {

	@Autowired
	TransactionService transactionService;
	
	@KafkaListener(
			topics = "${topic.customer.name:bootcoinT}",
			containerFactory = "kafkaListenerContainerFactory",
	groupId = "grupo1")
	public void consumer(@Payload BootCoinCreatedEvent event , @Headers MessageHeaders headers) {
		BootCoinCreatedEvent customerCreatedEvent = (BootCoinCreatedEvent) event;
		log.info("Received Customer created event .... with Id={}, data={}",
				customerCreatedEvent.getId(),
				customerCreatedEvent.getData().toString());
		
		TraBootCoin tBC = new TraBootCoin();
		
		tBC = customerCreatedEvent.getData();
		
		if (tBC.getTypeTra()==1) {
			Mono <Transactions> Tat = transactionService.Buy_BootCoin(tBC);
			Tat.subscribe();
		}else {
			Mono <Transactions> Tat = transactionService.Sold_BootCoin(tBC);
			Tat.subscribe();
		}
		
		
	}
	
}
