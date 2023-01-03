package com.example.webservice.controllers;

import com.example.webservice.models.Brand;
import com.example.webservice.payloads.BrandDto;
import com.example.webservice.services.BrandService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addBrand(@Valid @RequestBody BrandDto brandDto) {
        ApiResponse apiResponse = service.addBrand(brandDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(service.getAllBrands());
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long brandId) {
        return ResponseEntity.ok(service.getBrandById(brandId));
    }

    @GetMapping("/{brandName}")
    public ResponseEntity<Brand> getBrandByBrandName(@PathVariable String brandName) {
        return ResponseEntity.ok(service.getBrandByBrandName(brandName));
    }

    @PutMapping("/{brandId}")
    public HttpEntity<?> updateBrandById(@PathVariable Long brandId,
                                         @Valid @RequestBody BrandDto brandDto) {
        ApiResponse apiResponse = service.updateBrandById(brandId, brandDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{brandId}")
    public HttpEntity<?> deleteBrand(@PathVariable Long brandId) {
        ApiResponse apiResponse = service.deleteBrand(brandId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }
}