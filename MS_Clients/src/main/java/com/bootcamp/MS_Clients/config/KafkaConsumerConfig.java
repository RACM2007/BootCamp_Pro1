package com.bootcamp.MS_Clients.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.bootcamp.MS_Clients.events.ClientCreatedEvent;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	private final String bootstrapAddress = "localhost:9092";
	
	@Bean
    public ConsumerFactory<String, ClientCreatedEvent> consumerFactory() {
		Map<String, Object> config = new HashMap<>();
        
		JsonDeserializer<ClientCreatedEvent> deserializer = new JsonDeserializer<>(ClientCreatedEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
		        
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo1");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
                
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
	
    }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, ClientCreatedEvent>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, ClientCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
	
}
