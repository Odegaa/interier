package uz.interier.models;

import uz.interier.models.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "output_product")
public class OutputProducts extends BaseEntity {

    @OneToMany
    @NotNull
    @Column(name = "products_id")
    @ToString.Exclude
    private List<Product> products;

    @NotNull
    @Column(name = "amount")
    private Long amount;


}