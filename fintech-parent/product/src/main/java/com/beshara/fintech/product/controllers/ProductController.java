package com.beshara.fintech.product.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beshara.fintech.product.domain.ProductRepository;
import com.beshara.fintech.product.domain.model.Product;

@RestController
@RequestMapping("/product/api")
public class ProductController {

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
		return product ;
		
	}
	
	
}
