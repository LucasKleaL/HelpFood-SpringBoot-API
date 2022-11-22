package com.helpfood.productservice.product.repository;

import com.helpfood.productservice.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}