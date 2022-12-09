package com.example.webservice.repositories;

import com.example.webservice.models.InputProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputProductsRepository extends JpaRepository<InputProducts, Long> {

}