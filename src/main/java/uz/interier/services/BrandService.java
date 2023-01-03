package com.example.webservice.services;

import com.example.webservice.models.Brand;
import com.example.webservice.payloads.BrandDto;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrands();

    Brand getBrandById(Long brandId);

    Brand getBrandByBrandName(String brandName);

    ApiResponse addBrand(BrandDto brandDto);

    ApiResponse updateBrandById(Long brandId, BrandDto brandDto);

    ApiResponse deleteBrand(Long brandId);
}