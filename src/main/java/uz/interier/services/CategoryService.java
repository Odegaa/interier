package com.example.webservice.services;

import com.example.webservice.models.Category;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);

    Category getCategoryByCategoryName(String categoryName);

    ApiResponse addCategory(Category category);

    ApiResponse updateCategoryById(Long categoryId, Category category);

    ApiResponse deleteCategory(Long categoryId);
}