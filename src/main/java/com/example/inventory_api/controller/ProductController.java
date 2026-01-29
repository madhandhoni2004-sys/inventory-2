package com.example.inventory_api.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory_api.entity.Product;
import com.example.inventory_api.enums.Category;
import com.example.inventory_api.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
 
    private final ProductService service;
 
    public ProductController(ProductService service) {
        this.service = service;
    }
 
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product saved = service.createProduct(product);
        return ResponseEntity
                .created(URI.create("/api/products/" + saved.getId()))
                .body(saved);
    }
 
    @GetMapping
    public ResponseEntity<List<Product>> getAll(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) BigDecimal maxPrice
    ) {
        return ResponseEntity.ok(service.getAllProducts(category, maxPrice));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {
 
        return ResponseEntity.ok(service.updateProduct(id, product));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
