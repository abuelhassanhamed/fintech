package com.beshara.fintech.product.domain;

import org.springframework.data.repository.CrudRepository;

import com.beshara.fintech.product.domain.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
