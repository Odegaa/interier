package com.example.webservice.repositories;

import com.example.webservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductsByProductName(String productName);

    Product getProductsByPrice(BigDecimal price);

}