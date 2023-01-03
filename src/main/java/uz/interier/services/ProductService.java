package uz.interier.services;

import uz.interier.models.Product;
import uz.interier.payloads.ProductDto;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(Long productId);

    Product getProductByProductName(String productName);

    ApiResponse addProduct(ProductDto productDto);

    ApiResponse updateProductById(Long productId, ProductDto productDto);

    ApiResponse deleteProduct(Long productId);

}