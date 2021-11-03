package com.ratz.controller;

import com.ratz.entity.Product;
import com.ratz.service.ProductService;
import com.ratz.service.ProductServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return this.productService.findProductById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with the id " + id + " not found."));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody @Valid Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Integer id) {
        productService.findProductById(id).map(product -> {
            productService.deleteProductById(id);
            return product;
        }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with the id " + id + " not found."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateProductById(@PathVariable Integer id,@RequestBody @Valid Product product){

        productService.findProductById(id).map(productUpdated -> {
            product.setId(productUpdated.getId());
            productService.saveProduct(product);
            return product;
        }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with the id " + id + " not found."));
    }

    @GetMapping("/find")
    public List<Product> findProducts (Product filter){

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter,matcher);
        return productService.findAllProducts(example);
    }
}
