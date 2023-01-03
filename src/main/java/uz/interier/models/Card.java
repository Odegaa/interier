package com.example.webservice.models;

import com.example.webservice.models.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "card")
public class Card extends BaseEntity {

    @NotNull
    @Column(name = "user_for_card")
    private String userForCard;

    @NotNull
    @Column(unique = true, name = "card_number")
    private String cardNumber;

    @NotNull
    @Column(name = "cvc_code")
    private String cvcCode;

}
