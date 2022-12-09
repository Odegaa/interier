package com.example.webservice.repositories;

import com.example.webservice.models.OutputProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputProductsRepository extends JpaRepository<OutputProducts, Long> {
}