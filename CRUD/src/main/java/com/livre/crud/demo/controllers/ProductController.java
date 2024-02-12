package com.livre.crud.demo.controllers;

import com.livre.crud.demo.domain.product.Product;
import com.livre.crud.demo.domain.product.ProductRepository;
import com.livre.crud.demo.domain.product.RequestProduct;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Optional;

@RestController
@RequestMapping("/Products")
public class ProductController {
    @Autowired
    private ProductRepository repository;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllProducts () {
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity setProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok(newProduct);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
        Optional<Product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@RequestParam String id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("Successful deleted");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    }

