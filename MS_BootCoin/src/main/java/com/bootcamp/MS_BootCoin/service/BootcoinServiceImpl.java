package com.bootcamp.MS_BootCoin.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.bootcamp.MS_BootCoin.Entity.Clients;
import com.bootcamp.MS_BootCoin.Entity.MSClientKaf;
import com.bootcamp.MS_BootCoin.Entity.NoClients;
import com.bootcamp.MS_BootCoin.Entity.TraBootCoin;
import com.bootcamp.MS_BootCoin.model.BootCoin;
import com.bootcamp.MS_BootCoin.repository.BootcoinRepository;
import com.bootcamp.MS_BootCoin.util.BootCoinCreatedEvent;
import com.bootcamp.MS_BootCoin.util.ClientCreatedEvent;
import com.bootcamp.MS_BootCoin.util.Event;
import com.bootcamp.MS_BootCoin.util.EventType;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BootcoinServiceImpl implements BootcoinService {

	@Autowired
	private BootcoinRepository bootcoinRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	private ReactiveStringRedisTemplate redisTemplate;
	
	@Autowired
	private KafkaTemplate<String, Event<?>> producer;

	@Autowired
	private MSClientKafRedis msClientKafRedis;
	
	@Value("${topic.customer.name:bootcoinT}")
	private String topicBootcoin;
	
	@Override
	public Mono<BootCoin> Currency_Change(double newchange) {
		BootCoin BC = new BootCoin();
		
		BC.setCurrency_Sol_Change(newchange);
		BC.setDateCreate(new Date());
		
		return bootcoinRepository.save(BC);
		
	}

	@Override
	public Mono<BootCoin> Inquiry() {
		AggregationResults<BootCoin> resultsAll = ResultAll();

		Mono<BootCoin> Mbc;
		
		try {
			Mbc = Mono.just(resultsAll.getMappedResults().get(0));
		}catch(Exception e){
			Mbc=null;
		}
		
		return Mbc;
		
	}
	
	private AggregationResults<BootCoin> ResultAll(){
		SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC,"DateCreate"));
		Aggregation aggregationAll = Aggregation.newAggregation(sortOperation);
		AggregationResults<BootCoin> resultsAll =
				mongoTemplate.aggregate(aggregationAll,"BootCoin", BootCoin.class);
		return resultsAll;
	}

	@Override
	public Flux<BootCoin> InquiryAll() {
		return bootcoinRepository.findAll();
	}

	@Override
	public String RegistryUser_Cli(Clients cli) {
		
		MSClientKaf MS = new MSClientKaf();
		
		MS.setIsclient(true);
		MS.setCli(cli);
		
		Event created = new ClientCreatedEvent();
		
		created.setData(MS);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());
		
		Message<Event> message = MessageBuilder
	            .withPayload(created)
	            .setHeader(KafkaHeaders.TOPIC, topicBootcoin)
	            .build();

		msClientKafRedis.Save_Client_Redis(cli.getCodClient(), MS);
		
	    this.producer.send(message);
		
		return "Usuario Registrado Client";
	}

	@Override
	public String RegistryUser_NoCli(NoClients Ncli) {
		MSClientKaf MS = new MSClientKaf();
		
		MS.setIsclient(false);
		MS.setNoCli(Ncli);
		
		Event created = new ClientCreatedEvent();
		
		created.setData(MS);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());
		
		Message<Event> message = MessageBuilder
	            .withPayload(created)
	            .setHeader(KafkaHeaders.TOPIC, topicBootcoin)
	            .build();

		msClientKafRedis.Save_Client_Redis(Ncli.getCodNoClient(), MS);
		
	    this.producer.send(message);
		
		return "Usuario Registrado No Client";
	}

	@Override
	public String Buy_BootCoin(TraBootCoin tBC) {
				
		tBC.setTypeTra(1);
		tBC.setDateCreate(new Date());
		
		Event created = new BootCoinCreatedEvent();
		
		created.setData(tBC);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());
		
		Message<Event> message = MessageBuilder
	            .withPayload(created)
	            .setHeader(KafkaHeaders.TOPIC, topicBootcoin)
	            .build();
		
	    this.producer.send(message);
		
		return "Compra Registrada";
	}

	@Override
	public String Sold_BootCoin(TraBootCoin tBC) {
		
		tBC.setTypeTra(2);
		
		tBC.setDateCreate(new Date());
		
		Event created = new BootCoinCreatedEvent();
		
		created.setData(tBC);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());
		
		//this.producer.send(topicBootcoin,created);
		
		Message<Event> message = MessageBuilder
	            .withPayload(created)
	            .setHeader(KafkaHeaders.TOPIC, topicBootcoin)
	            .build();
		
	    this.producer.send(message);
		
		return "Venta Registrada";
	}
	
}
