package com.beshara.fintech.product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beshara.fintech.product.domain.ProductRepository;
import com.beshara.fintech.product.domain.model.Product;

@SpringBootApplication


public class ProductApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	
	
}
