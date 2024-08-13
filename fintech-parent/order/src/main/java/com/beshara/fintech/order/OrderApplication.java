package com.beshara.fintech.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;

import com.beshara.fintech.order.domain.events.OrderCreatedEvent;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@SpringBootApplication
//@Deployment(resources = "classpath:order.bpmn")
public class OrderApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(OrderApplication.class, args);
		System.out.println("ssss");
	
	}

//    @JobWorker(type = "OrderReviewedEvent")
//	public void handleJobFoo() {
//	  System.out.println("order reviewed 4");
//	}
    
//	@KafkaListener(id = "ORDERGROUP", topics = "order-flow")
//    public void listen(String in) {
//        System.out.println(in);
//    }

	
}
