package uz.interier.controllers;

import uz.interier.models.Product;
import uz.interier.payloads.ProductDto;
import uz.interier.services.ProductService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductService service;

    @Autowired
    public ProductsController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addProducts(@Valid @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = service.addProduct(productDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getProduct(productId));
    }

    @GetMapping(value = "/{productName}")
    public ResponseEntity<Product> getProductByProductName(@PathVariable String productName) {
        return ResponseEntity.ok(service.getProductByProductName(productName));
    }

    @PutMapping(value = "/{productId}")
    public HttpEntity<?> updateProduct(@PathVariable Long productId,
                                       @Valid @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = service.updateProductById(productId, productDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping(value = "/{productId}")
    public HttpEntity<?> deleteProduct(@PathVariable Long productId) {
        ApiResponse apiResponse = service.deleteProduct(productId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

}

