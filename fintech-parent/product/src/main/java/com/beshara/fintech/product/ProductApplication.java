package com.beshara.fintech.product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beshara.fintech.product.domain.ProductRepository;
import com.beshara.fintech.product.domain.events.OrderCreatedEvent;
import com.beshara.fintech.product.domain.model.Product;

@SpringBootApplication


public class ProductApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Bean
	public Consumer<OrderCreatedEvent> handleOrderCreation() {
		return (e)->{System.out.println(e.getPayload().getId());};
	}
	
//	@KafkaListener(id = "myId", topics = "order-flow")
//    public void listen(OrderCreatedEvent in) {
//        System.out.println(in.getPayload().getId());
//    }

}
