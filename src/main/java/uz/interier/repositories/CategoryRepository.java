package com.example.webservice.repositories;

import com.example.webservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoryByCategoryName(String categoryName);
    Category findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);

}