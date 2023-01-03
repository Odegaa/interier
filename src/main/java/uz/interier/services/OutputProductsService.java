package uz.interier.services;

import uz.interier.models.OutputProducts;
import uz.interier.payloads.OutputProductsDto;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface OutputProductsService {

    List<OutputProducts> getAllOutputProducts();

    OutputProducts getOutputProductById(Long outputProducts);

    ApiResponse addOutputProduct(OutputProductsDto outputProductsDto);

    ApiResponse updateOutputProduct(Long outputProductId, OutputProductsDto outputProductsDto);

    ApiResponse deleteOutputProduct(Long outputProductsId);

}