package com.example.inventory_api.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.inventory_api.entity.Product;
import com.example.inventory_api.enums.Category;

public interface ProductService {
	
	Product createProduct(Product product);
	Product getProductById(Long id);
	
	List<Product>getAllProducts(Category category, BigDecimal maxPrice);
	
	Product updateProduct(Long id, Product product);
	void deleteProduct(Long id);
	 

}
