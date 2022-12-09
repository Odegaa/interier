package com.example.webservice.models;

import com.example.webservice.models.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @NotNull
    @Column(unique = true, name = "street_name")
    private String streetName;

    @NotNull
    @Column(name = "house_number")
    private Integer houseNumber;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "city_id")
    private City cityId;

}
