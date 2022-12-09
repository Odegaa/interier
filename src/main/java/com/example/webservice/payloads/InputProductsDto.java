package com.example.webservice.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductsDto {
    private List<Long> productsId;
    private Long amount;
}