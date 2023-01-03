package uz.interier.services;

import uz.interier.models.InputProducts;
import uz.interier.payloads.InputProductsDto;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface InputProductsService {

    List<InputProducts> getAllInputProducts();

    InputProducts getInputProductsById(Long inputProductsId);

    ApiResponse addInputProducts(InputProductsDto inputProductsDto);

    ApiResponse updateInputProducts(Long inputProductsId, InputProductsDto inputProductsDto);

    ApiResponse deleteInputProducts(Long inputProductsId);
}