package com.beshara.fintech.order.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beshara.fintech.order.domain.model.Order;
import com.beshara.fintech.order.domain.model.OrderRepository;
import com.beshara.fintech.tracing.TracimgContextAccessor;
import com.beshara.fintech.tracing.TracingContextHolder;
import com.fintech.events.order.OrderCreatedEvent;
import com.fintech.events.order.OrderDeletedEvent;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.context.Context;

//import io.cloudevents.CloudEvent;

@RestController
@CrossOrigin

@RequestMapping("/fintech/order/api")
public class OrderController {
	
	
	@Autowired
    private ZeebeClient zeebeClient;

	
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private StreamBridge orderPublisher;
	
	@Autowired
	OpenTelemetry openTelemetry;

	
	@GetMapping(value = "/order")
	public List<Order> getOrder() {
		
	//	CloudEvent event;
	//	CloudEventData data=
//		  CloudEvent event = CloudEventBuilder.v1()
//	                .withId("hello")
//	                .withType("example.kafka")
//	                .withSource(URI.create("http://localhost"))
//	                .build();
		return (List<Order>) orderRepo.findAll();
	}

	
@PostMapping(value = "/order")	
public Order placeOrder(@RequestBody Order order) {
		
	
	TracingContextHolder holder =new TracingContextHolder();
	final TracimgContextAccessor exportedSpanData = TracimgContextAccessor.createSetter();
	io.opentelemetry.context.propagation.TextMapPropagator textMapPropagator = openTelemetry.getPropagators().getTextMapPropagator();
   textMapPropagator.inject(Context.current(), holder, exportedSpanData);
    
    
	
	
	//	 order=new Order();
		order.setCustomerId(UUID.randomUUID().toString());
	//	order.setId(UUID.randomUUID().toString());
	//	order.setOrderDate(new Date());
	//	OrderLine orderLine=new OrderLine();
		//orderLine.setOrderId(order.getId());
	//	orderLine.setProductId(UUID.randomUUID().toString());
	//	orderLine.setQuantity(3);
	//	order.getOrderDetails().add(orderLine);
		order=orderRepo.save(order);
		OrderCreatedEvent orderCreatedEvent=new OrderCreatedEvent();
		com.fintech.events.order.Order ord=new com.fintech.events.order.Order();
		ord.setOrderId(order.getId());
		ord.setTotalPrice(100l);
		orderCreatedEvent.setPayload(ord);
		orderPublisher.send("order-flow-binding", orderCreatedEvent);
		
		
		ProcessInstanceEvent processInstance =
		        zeebeClient
		            .newCreateInstanceCommand()
		            .bpmnProcessId("order-process")
		            .latestVersion()
		            .variables(order)
		            .variable("id", ord.getOrderId())
		            .variable("traceParent",(String) holder.getContext().get("traceparent"))
		            .send()
		            .join(); // blocking call!
		
		
		return order;
	}
	
 @DeleteMapping (value = "/order")	
 
public boolean deleteOrder(@RequestBody Order order) {
		
	//	 order=new Order();
		order.setCustomerId(UUID.randomUUID().toString());
	//	order.setId(UUID.randomUUID().toString());
	//	order.setOrderDate(new Date());
	//	OrderLine orderLine=new OrderLine();
		//orderLine.setOrderId(order.getId());
	//	orderLine.setProductId(UUID.randomUUID().toString());
	//	orderLine.setQuantity(3);
	//	order.getOrderDetails().add(orderLine);
		orderRepo.delete(order);
		OrderDeletedEvent orderDeletedEvent=new OrderDeletedEvent();
		com.fintech.events.order.Order ord=new com.fintech.events.order.Order();
		ord.setOrderId(order.getId());
		ord.setTotalPrice(100l);
		orderDeletedEvent.setPayload(ord);
		orderPublisher.send("order-flow-binding", orderDeletedEvent);
		return true;
	}
	
}
