package com.ratz.service;

import com.ratz.entity.Product;
import com.ratz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAllProducts(Example example) {
        return repository.findAll(example);
    }
}
