package com.example.inventory_api.service;

import com.example.inventory_api.entity.Product;
import com.example.inventory_api.enums.Category;
import com.example.inventory_api.exception.ResourceNotFoundException;
import com.example.inventory_api.repository.ProductRepository;
import org.springframework.stereotype.Service;
 
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{


    private final ProductRepository repository;
 
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }
 
    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }
 
    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
 
    @Override
    public List<Product> getAllProducts(Category category, BigDecimal maxPrice) {
 
        if (category != null && maxPrice != null) {
            return repository.findByCategoryAndPriceLessThanEqual(category, maxPrice);
        }
        if (category != null) {
            return repository.findByCategory(category);
        }
        if (maxPrice != null) {
            return repository.findByPriceLessThanEqual(maxPrice);
        }
 
        return repository.findAll();
    }
 
    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
 
        Product existing = getProductById(id);
 
        if (updatedProduct.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
 
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setQuantity(updatedProduct.getQuantity());
        existing.setCategory(updatedProduct.getCategory());
 
        return repository.save(existing);
    
    }
 
    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        repository.delete(product);
    }
}