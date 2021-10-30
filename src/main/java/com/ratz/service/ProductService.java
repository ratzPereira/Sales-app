package com.ratz.service;

import com.ratz.entity.Product;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findProductById(Integer id);
    Product saveProduct(Product product);
    void deleteProductById(Integer id);
    List<Product> findAllProducts(Example example);
}
