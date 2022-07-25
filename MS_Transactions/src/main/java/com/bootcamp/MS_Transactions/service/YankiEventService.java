package com.bootcamp.MS_Transactions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bootcamp.MS_Transactions.Entity.Pay;
import com.bootcamp.MS_Transactions.events.PayCreatedEvent;
import com.bootcamp.MS_Transactions.model.Transactions;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class YankiEventService {

	@Autowired
	TransactionService transactionService;
	
	@KafkaListener(
			topics = "${topic.customer.name:yankiT}",
			containerFactory = "kafkaListenerContainerFactory2",
	groupId = "grupo1")
	public void consumer(@Payload PayCreatedEvent event , @Headers MessageHeaders headers) {
		PayCreatedEvent customerCreatedEvent = (PayCreatedEvent) event;
		log.info("Received Customer created event .... with Id={}, data={}",
				customerCreatedEvent.getId(),
				customerCreatedEvent.getData().toString());
		
		Pay payO = new Pay();
		
		payO = customerCreatedEvent.getData();
		
		if (payO.getType()==1) {
			Mono <Transactions> Tat = transactionService.Send_Pay(payO);
			Tat.subscribe();
		}else {
			Mono <Transactions> Tat = transactionService.Rece_Pay(payO);
			Tat.subscribe();
		}
		
		
	}
	
}
