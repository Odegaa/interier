package uz.interier.models;

import uz.interier.models.base.BaseEntity;
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
@Table(name = "category")
public class Category extends BaseEntity {

    @NotNull
    @Column(unique = true, name = "category_name")
    private String categoryName;

}
