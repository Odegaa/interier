package uz.interier.services;

import uz.interier.models.Category;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);

    Category getCategoryByCategoryName(String categoryName);

    ApiResponse addCategory(Category category);

    ApiResponse updateCategoryById(Long categoryId, Category category);

    ApiResponse deleteCategory(Long categoryId);
}