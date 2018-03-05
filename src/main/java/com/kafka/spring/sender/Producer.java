package com.kafka.spring.sender;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class Producer {
	
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private KafkaTemplate<String, Serializable> kafkaTemplate;
	 
	public void sendMessage(String topic, String key, String msg) {
		logger.info("sending message to topic:{} , length:{}",topic, msg.length());
		ListenableFuture<SendResult<String, Serializable>> future = kafkaTemplate.send(topic, key, msg);
		
		future.addCallback(  
		          new ListenableFutureCallback<SendResult<String, Serializable>>() {  
		            @Override  
		            public void onSuccess(SendResult<String, Serializable> response) {  
		            	logger.info("Successfully sent message for key:{}", response.getProducerRecord().key());  
		            }  
		  
		            @Override  
		            public void onFailure(Throwable t) {
		            	logger.error("Failure:",t);
		            }  
		        });  
	}
}
