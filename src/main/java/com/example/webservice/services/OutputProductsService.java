package com.example.webservice.services;

import com.example.webservice.models.OutputProducts;
import com.example.webservice.payloads.OutputProductsDto;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface OutputProductsService {

    List<OutputProducts> getAllOutputProducts();

    OutputProducts getOutputProductById(Long outputProducts);

    ApiResponse addOutputProduct(OutputProductsDto outputProductsDto);

    ApiResponse updateOutputProduct(Long outputProductId, OutputProductsDto outputProductsDto);

    ApiResponse deleteOutputProduct(Long outputProductsId);

}