package com.example.webservice.models;

import com.example.webservice.models.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(name = "about")
    private String about;

    @NotNull
    @Column(name = "characteristic")
    private String characteristic;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "brand_id")
    private Brand brands;

    @OneToMany
    @NotNull
    @JoinColumn(name = "attachment_id")
    @ToString.Exclude
    private List<Attachment> attachmentsId;

    @NotNull
    @Column(unique = true, name = "code_for_product")
    private String codeForProduct;

}