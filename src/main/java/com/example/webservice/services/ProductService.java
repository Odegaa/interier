package com.example.webservice.services;

import com.example.webservice.models.Product;
import com.example.webservice.payloads.ProductDto;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(Long productId);

    Product getProductByProductName(String productName);

    ApiResponse addProduct(ProductDto productDto);

    ApiResponse updateProductById(Long productId, ProductDto productDto);

    ApiResponse deleteProduct(Long productId);

}