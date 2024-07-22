package com.beshara.fintech.product.controllers;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beshara.fintech.product.domain.model.Order;
import com.beshara.fintech.product.domain.model.OrderLine;
import com.beshara.fintech.product.domain.model.OrderRepository;

@RestController
@RequestMapping("/fintech/order/api")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@GetMapping(value = "/order")
	public Order getOrder() {
		
		return null;
	}

	@GetMapping(value = "/demo")
	public Order getDemoOrder() {
		
		Order order=new Order();
		order.setCustomerId(UUID.randomUUID().toString());
		order.setId(UUID.randomUUID().toString());
		order.setOrderDate(new Date());
		OrderLine orderLine=new OrderLine();
		orderLine.setOrderId(order.getId());
		orderLine.setProductId(UUID.randomUUID().toString());
		orderLine.setQuantity(3);
		
		return null;
	}
	@PostMapping(value = "/order")	
public Order placeOrder(@RequestBody Order order) {
		
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
		return true;
	}
	
}
