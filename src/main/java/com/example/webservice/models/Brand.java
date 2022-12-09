package com.example.webservice.models;

import com.example.webservice.models.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    @NotNull
    @Column(unique = true, name = "brand_name")
    private String brandName;

    @JoinColumn(name = "product_list")
    @ManyToMany
    @ToString.Exclude
    private List<Product> productList;

}