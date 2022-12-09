package com.example.webservice.services.impls;

import com.example.webservice.models.Category;
import com.example.webservice.models.templates.Status;
import com.example.webservice.repositories.CategoryRepository;
import com.example.webservice.services.CategoryService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category getCategoryByCategoryName(String categoryName) {
        Category byCategoryName = categoryRepository.findByCategoryName(categoryName);
        if (byCategoryName == null) {
            return null;
        }
        return categoryRepository.getCategoryByCategoryName(categoryName);
    }

    @Override
    public ApiResponse addCategory(Category category) {
        boolean byCategoryName = categoryRepository.existsByCategoryName(category.getCategoryName());
        if (byCategoryName) {
            return new ApiResponse("This category already exist!", false);
        }
        Category newCategory = new Category();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setStatus(Status.ACTIVE);
        categoryRepository.save(newCategory);
        return new ApiResponse("Category successfully SAVED!", true);
    }

    @Override
    public ApiResponse updateCategoryById(Long categoryId, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category updatingCategory = optionalCategory.get();
            updatingCategory.setCategoryName(category.getCategoryName());
            updatingCategory.setStatus(Status.ACTIVE);
            categoryRepository.save(updatingCategory);
            return new ApiResponse("Category successfully UPDATED!", true);
        }
        return new ApiResponse("Category not found!", false);
    }

    @Override
    public ApiResponse deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return new ApiResponse("Category DELETED!", true);
        }
        return new ApiResponse("Category not found!", false);
    }
}