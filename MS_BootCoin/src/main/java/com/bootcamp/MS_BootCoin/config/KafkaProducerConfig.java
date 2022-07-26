package com.bootcamp.MS_BootCoin.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.bootcamp.MS_BootCoin.util.Event;

@Configuration
public class KafkaProducerConfig {

	//IP Kafka Server
	private final String bootstrapAddress = "localhost:9092";
	
	//kafka configuration
	
	@Bean
	public ProducerFactory<String, Event<?>> producerFactory(){
		Map<String, Object> configProps = new HashMap<>();
		
		configProps.put(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapAddress
				);
		
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
				StringSerializer.class);
		
		configProps.put(
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(configProps);
	}
	
	@Bean
	public KafkaTemplate<String, Event<?>> KafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
	
}
