package com.beshara.fintech.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication

@Deployment(resources = "classpath:order.bpmn")
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
		System.out.println("ssss");
	}

}
