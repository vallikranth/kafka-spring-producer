package com.kafka.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class ProducerApp {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApp.class, args);
	}

}
