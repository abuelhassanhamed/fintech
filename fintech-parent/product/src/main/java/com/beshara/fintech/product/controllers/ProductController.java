package com.beshara.fintech.product.controllers;

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

import com.beshara.fintech.product.domain.ProductRepository;
import com.beshara.fintech.product.domain.model.Product;
import com.fintech.events.product.ProductCreatedEvent;

@RestController
@RequestMapping("/product/api")
@CrossOrigin
public class ProductController {

	@Autowired
	private StreamBridge productPublisher;
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping(value = "/product")
	Product getProduct()
	{
		Product product=new Product();
		product.setId(UUID.randomUUID().toString());
		product.setDescription("help me babe");
		return product ;
		
	}


	@GetMapping(value = "/products")
	List<Product> getProducts()
	{
	
		
		return (List<Product>) repo.findAll();
		
	}
	
	 
	
	@PostMapping(value = "/product")
	Product addProduct(@RequestBody Product product)
	{
		product=repo.save(product);
		
		ProductCreatedEvent event=new ProductCreatedEvent();
		com.fintech.events.product.Product prod=new com.fintech.events.product.Product();
		prod.setId(product.getId());
		prod.setDescription(product.getDescription());
		prod.setPrice(product.getPrice());
		event.setPayload(prod);
		productPublisher.send("product-flow-binding", event);
		return product ;
		
	}
	
	@DeleteMapping(value = "/product")
	Product deleteProduct(@RequestBody Product product)
	{
		repo.delete(product);
		return product ;
		
	}
	
	
}
