package com.MS_Yanki.MS_Yanki.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.MS_Yanki.MS_Yanki.Entity.Clients;
import com.MS_Yanki.MS_Yanki.Entity.CheckUser;
import com.MS_Yanki.MS_Yanki.Entity.MSClientKaf;
import com.MS_Yanki.MS_Yanki.Entity.NoClients;
import com.MS_Yanki.MS_Yanki.Entity.Pay;
import com.MS_Yanki.MS_Yanki.util.ClientCreatedEvent;
import com.MS_Yanki.MS_Yanki.util.Event;
import com.MS_Yanki.MS_Yanki.util.EventType;
import com.MS_Yanki.MS_Yanki.util.PayCreatedEvent;

import reactor.core.publisher.Mono;

@Service
public class YankiServiceImpl implements YankiService{

	@Autowired
	private KafkaTemplate<String, Event<?>> producer;
	
	@Value("${topic.customer.name:yankiT}")
	private String topicYanki;
	
	@Override
	public String Registry_User_Cli(Clients cli) {
		
		MSClientKaf MS = new MSClientKaf();
		
		MS.setIsclient(true);
		MS.setCli(cli);
		
		Event created = new ClientCreatedEvent();
		
		created.setData(MS);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());
		
		//this.producer.send(topicBootcoin,created);
		
		Message<Event> message = MessageBuilder
	            .withPayload(created)
	            .setHeader(KafkaHeaders.TOPIC, topicYanki)
	            .build();
		
	    this.producer.send(message);
		
		return "Registered User";
	}

	@Override
	public String Registry_User_NoCli(NoClients Ncli) {
		MSClientKaf MS = new MSClientKaf();
		
		MS.setIsclient(false);
		MS.setNoCli(Ncli);
		
		Event created = new ClientCreatedEvent();
		
		created.setData(MS);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());
		
		//this.producer.send(topicBootcoin,created);
		
		Message<Event> message = MessageBuilder
	            .withPayload(created)
	            .setHeader(KafkaHeaders.TOPIC, topicYanki)
	            .build();
		
	    this.producer.send(message);
		
		return "Registered User";
	}

	@Override
	public String Send_Pay(Pay payO) {
		
		Event created = new PayCreatedEvent();
		
		payO.setType(1);
		
		created.setData(payO);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());
		
		//this.producer.send(topicBootcoin,created);
		
		Message<Event> message = MessageBuilder
	            .withPayload(created)
	            .setHeader(KafkaHeaders.TOPIC, topicYanki)
	            .build();
		
	    this.producer.send(message);
		
		return "Registered payment";
	}

	@Override
	public String Rece_Pay(Pay payO) {
		
		Event created = new PayCreatedEvent();
		
		payO.setType(2);
		
		created.setData(payO);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());
		
		//this.producer.send(topicBootcoin,created);
		
		Message<Event> message = MessageBuilder
	            .withPayload(created)
	            .setHeader(KafkaHeaders.TOPIC, topicYanki)
	            .build();
		
	    this.producer.send(message);
		
		return "Received payment received";
	}


}
