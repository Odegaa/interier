package com.example.webservice.services.impls;

import com.example.webservice.models.Brand;
import com.example.webservice.models.Product;
import com.example.webservice.models.templates.Status;
import com.example.webservice.payloads.BrandDto;
import com.example.webservice.repositories.BrandRepository;
import com.example.webservice.repositories.ProductRepository;
import com.example.webservice.services.BrandService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final ProductRepository productRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return repository.findAll();
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return repository.findById(brandId).orElse(null);
    }

    @Override
    public Brand getBrandByBrandName(String brandName) {
        Brand byBrandName = repository.findByBrandName(brandName);
        if (byBrandName == null) {
            return null;
        }
        return repository.getBrandByBrandName(brandName);
    }

    @Override
    public ApiResponse addBrand(BrandDto brandDto) {
        boolean byBrandName = repository.existsByBrandName(brandDto.getBrandName());
        if (byBrandName) {
            return new ApiResponse("This brand name already exist!", false);
        }
        Brand brand = new Brand();
        brand.setBrandName(brandDto.getBrandName());
        brand.setStatus(Status.ACTIVE);

        List<Long> list = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        for (Long aLong : brandDto.getProductsId()) {
            list.add(aLong);
            for (Long value : list) {
                Optional<Product> productOptional = productRepository.findById(value);
                if (productOptional.isPresent()) {
                    productList.add(productOptional.get());
                    brand.setProductList(productList);
                    repository.save(brand);
                    return new ApiResponse("Brand successfully SAVED!", true);
                }
                return new ApiResponse("Product not found!", false);
            }
        }
        return new ApiResponse("Brand not found!", false);
    }

    @Override
    public ApiResponse updateBrandById(Long brandId, BrandDto brandDto) {
        Optional<Brand> optionalBrand = repository.findById(brandId);
        if (optionalBrand.isPresent()) {
            boolean byBrandName = repository.existsByBrandName(brandDto.getBrandName());
            if (byBrandName) {
                return new ApiResponse("This brand already exist!", false);
            }
            Brand updatingBrand = optionalBrand.get();
            updatingBrand.setBrandName(brandDto.getBrandName());
            updatingBrand.setStatus(Status.ACTIVE);

            List<Long> list = new ArrayList<>();
            List<Product> productList = new ArrayList<>();

            for (Long aLong : brandDto.getProductsId()) {
                list.add(aLong);
                for (Long value : list) {
                    Optional<Product> productOptional = productRepository.findById(value);
                    if(productOptional.isPresent()) {
                        productList.add(productOptional.get());
                        updatingBrand.setProductList(productList);
                        repository.save(updatingBrand);
                        return new ApiResponse("Brand successfully UPDATED!", true);
                    }
                    return new ApiResponse("Product not found!", false);
                }
            }
        }
        return new ApiResponse("Brand not found!", false);
    }

    @Override
    public ApiResponse deleteBrand(Long brandId) {
        Optional<Brand> optional = repository.findById(brandId);
        if (optional.isPresent()) {
            repository.deleteById(brandId);
            return new ApiResponse("Brand DELETED!", true);
        }
        return new ApiResponse("Brand not found!", false);
    }
}