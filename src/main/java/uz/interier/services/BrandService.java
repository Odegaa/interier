package uz.interier.services;

import uz.interier.models.Brand;
import uz.interier.payloads.BrandDto;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrands();

    Brand getBrandById(Long brandId);

    Brand getBrandByBrandName(String brandName);

    ApiResponse addBrand(BrandDto brandDto);

    ApiResponse updateBrandById(Long brandId, BrandDto brandDto);

    ApiResponse deleteBrand(Long brandId);
}