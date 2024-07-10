package com.beshara.fintech.product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beshara.fintech.product.domain.model.Product;

@SpringBootApplication
@RestController
@RequestMapping("/product/api")
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	
	
	@GetMapping(value = "/product")
	Product getProduct()
	{
		Product product=new Product();
		product.setId(UUID.randomUUID().toString());
		product.setDescription("demo");
		return product ;
		
	}


	@GetMapping(value = "/products")
	List<Product> getProducts()
	{
		List products=new ArrayList<Product>();
		
		Product product=new Product();
		product.setId(UUID.randomUUID().toString());
		product.setDescription("skaffold with port forward");
		products.add(product);
		products.add(product);
		products.add(product);
		products.add(product);
		products.add(product);
		products.add(product);
		products.add(product);
	
		return products ;
		
	}
	
	 
	
	@PostMapping(value = "/product")
	Product addProduct(@RequestBody Product product)
	{
		System.out.println(product.getId());
		return product ;
		
	}
	
	@PostMapping(value = "/product/update")
	Product updateProduct(@RequestBody Product product)
	{
		System.out.println(product.getId());
		return product ;
		
	}
	
	
}
