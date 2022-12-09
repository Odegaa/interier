package com.example.webservice.models;

import com.example.webservice.models.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "input_product")
public class InputProducts extends BaseEntity {

    @OneToMany
    @NotNull
    @JoinColumn(name = "products_id")
    @ToString.Exclude
    private List<Product> products;

    @NotNull
    @Column(name = "amount")
    private Long amount;

}
