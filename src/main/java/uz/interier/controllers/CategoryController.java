package uz.interier.controllers;

import uz.interier.models.Category;
import uz.interier.services.CategoryService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addCategory(@Valid @RequestBody Category category) {
        ApiResponse apiResponse = service.addCategory(category);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.getCategoryById(categoryId));
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<Category> getCategoryByCategoryName(@PathVariable String categoryName) {
        return ResponseEntity.ok(service.getCategoryByCategoryName(categoryName));
    }

    @PutMapping("/{categoryId}")
    public HttpEntity<?> updateCategoryById(@PathVariable Long categoryId,
                                            @Valid @RequestBody Category category) {
        ApiResponse apiResponse = service.updateCategoryById(categoryId, category);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{categoryId}")
    public HttpEntity<?> deleteCategory(@PathVariable Long categoryId) {
        ApiResponse apiResponse = service.deleteCategory(categoryId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }
}