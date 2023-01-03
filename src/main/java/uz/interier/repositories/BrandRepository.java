package com.example.webservice.repositories;

import com.example.webservice.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand getBrandByBrandName(String brandName);

    Brand findByBrandName(String brandName);

    boolean existsByBrandName(String brandName);
}