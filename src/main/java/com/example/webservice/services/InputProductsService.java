package com.example.webservice.services;

import com.example.webservice.models.InputProducts;
import com.example.webservice.payloads.InputProductsDto;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface InputProductsService {

    List<InputProducts> getAllInputProducts();

    InputProducts getInputProductsById(Long inputProductsId);

    ApiResponse addInputProducts(InputProductsDto inputProductsDto);

    ApiResponse updateInputProducts(Long inputProductsId, InputProductsDto inputProductsDto);

    ApiResponse deleteInputProducts(Long inputProductsId);
}