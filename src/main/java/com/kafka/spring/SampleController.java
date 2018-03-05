package com.kafka.spring;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.spring.sender.Producer;

@RestController
public class SampleController {
    @Autowired
	private Producer producer;
	
    private Random keyGenerator = new Random(25467);
    
	@RequestMapping("/")
    @ResponseBody
    public String sendSampleMessage() {
		String key=String.valueOf(keyGenerator.nextInt(500));
		String payload="Test Message from kafka test producer:"+key;
		producer.sendMessage("test",key, payload);
        return "sent to test topic, payload:"+payload;
    }
}
