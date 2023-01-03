package com.example.webservice.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private String about;
    private String characteristic;
    private BigDecimal price;
    private Long categoryId;
    private Long brandId;
    private List<Long> attachmentId;
}