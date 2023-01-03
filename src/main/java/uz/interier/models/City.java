package uz.interier.models;

import uz.interier.models.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "city")
public class City extends BaseEntity {

    @NotNull
    @Column(unique = true, name = "city_name")
    private String cityName;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "country_id")
    private Country countryId;

}
