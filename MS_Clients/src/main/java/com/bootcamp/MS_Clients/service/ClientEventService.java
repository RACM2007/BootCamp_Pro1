package com.bootcamp.MS_Clients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bootcamp.MS_Clients.Entity.MSClientKaf;
import com.bootcamp.MS_Clients.events.ClientCreatedEvent;
import com.bootcamp.MS_Clients.model.Clients;
import com.bootcamp.MS_Clients.model.NoClients;
import com.bootcamp.MS_Clients.repository.ClientRepository;
import com.bootcamp.MS_Clients.repository.NoClientRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ClientEventService {

	
	@Autowired
    private ClientRepository clientRepository;
	
	@Autowired
    private NoClientRepository noClientRepository;
	
	@KafkaListener(
			topics = "${topic.customer.name:bootcoinT}",
			containerFactory = "kafkaListenerContainerFactory",
	groupId = "grupo1")
	public void consumer(@Payload ClientCreatedEvent event , @Headers MessageHeaders headers) {
		ClientCreatedEvent customerCreatedEvent = (ClientCreatedEvent) event;
		log.info("Received Customer created event .... with Id={}, data={}",
				customerCreatedEvent.getId(),
				customerCreatedEvent.getData().toString());
		
		MSClientKaf MSC = customerCreatedEvent.getData();
		
		if (MSC.isIsclient()) {
			//Mono <Clients> Tat = clientService.save(MSC.getCli());
			Mono <Clients> Tat = clientRepository.save(MSC.getCli());
			Tat.subscribe();
		}else {
			Mono <NoClients> Tat = noClientRepository.save(MSC.getNoCli());
			Tat.subscribe();
		}
		
		
	}
	
}
