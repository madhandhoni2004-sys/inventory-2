package com.example.inventory_api.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory_api.entity.Product;
import com.example.inventory_api.enums.Category;

public interface ProductRepository extends JpaRepository<Product,Long>{
	
	List<Product>findByCategory(Category category);
	
	List<Product>findByPriceLessThanEqual(BigDecimal maxPrice);
	
	List<Product>findByCategoryAndPriceLessThanEqual(Category category,BigDecimal maxPrice);

}
