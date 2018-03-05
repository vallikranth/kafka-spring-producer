package com.kafka.spring.configuration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.kafka.spring")
@Component
public class KafkaProducerConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
    public ProducerFactory<String, Serializable> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          env.getProperty("producer.servers"));
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          JsonSerializer.class);
        configProps.put(
                ProducerConfig.ACKS_CONFIG, 
                env.getProperty("producer.acks"));
        configProps.put(
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 
                env.getProperty("producer.request.timeout.ms"));
        configProps.put(
                ProducerConfig.MAX_BLOCK_MS_CONFIG, 
                env.getProperty("producer.max.block.ms"));
        configProps.put(
                ProducerConfig.BATCH_SIZE_CONFIG, 
                env.getProperty("producer.batchSize.kb"));
        configProps.put(
                ProducerConfig.LINGER_MS_CONFIG, 
                env.getProperty("producer.linger.ms"));
        configProps.put(
                ProducerConfig.BUFFER_MEMORY_CONFIG, 
                env.getProperty("producer.buffer"));
        
        return new DefaultKafkaProducerFactory<>(configProps);
    }
 
    @Bean
    public KafkaTemplate<String, Serializable> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
