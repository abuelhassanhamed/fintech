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
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beshara.fintech.product.domain.ProductRepository;

import com.beshara.fintech.product.domain.model.Product;
import com.fintech.events.order.Event;
import com.fintech.events.order.Order;
import com.fintech.events.order.OrderCreatedEvent;
import com.fintech.events.order.OrderDeletedEvent;
import com.fintech.events.order.OrderEvent;
import com.fintech.events.order.OrderReviewedEvent;

import io.micrometer.observation.annotation.Observed;


@SpringBootApplication
public class ProductApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	
	
	
	@Bean
	@Observed(name="handleOrderEvent")
    public Consumer<OrderEvent> handleOrderEvent() {
		return (e)->{
			if (e instanceof OrderCreatedEvent)
			System.out.println("OrderCreatedEvent orderId : "+e.getClass().getCanonicalName()+" :" +e.getPayload().getOrderId());
			if (e instanceof OrderDeletedEvent)
				System.out.println("OrderDeletedEvent orderId : "+e.getClass().getCanonicalName()+" :" +e.getPayload().getOrderId());
			if (e instanceof OrderReviewedEvent)
				System.out.println("OrderReviewedEvent orderId : "+e.getClass().getCanonicalName()+" :" +e.getPayload().getOrderId());
			
			
		};
			
			
	}
	
//	@Bean
//	public Consumer<Event<Order>> handleOrderDeletion() {
//		return (e)->{
//			if (e instanceof OrderDeletedEvent)
//			System.out.println("OrderDeletedEvent orderId : "+e.getClass().getCanonicalName()+" :" +e.getPayload().getOrderId());
//			};
//	}
	
//	@KafkaListener(id = "myId", topics = "order-flow")
//    public void listen(OrderCreatedEvent in) {
//        System.out.println(in.getPayload().getId());
//    }
	
//	@Bean
//	public Consumer<ErrorMessage> myErrorHandler() {
//		return v -> {
//			System.out.print("this is the message handler");
//		};
//	}

}
