package uz.interier.models;

import uz.interier.models.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    @NotNull
    @Column(unique = true, name = "company_name")
    private String companyName;

    @NotNull
    @Column(name = "about")
    private String about;

    @NotNull
    @OneToMany
    @Column(name = "attachment_id")
    @ToString.Exclude
    private List<Attachment> attachment;

    @OneToMany
    @NotNull
    @JoinColumn(name = "products")
    @ToString.Exclude
    private List<Product> productList;

    @OneToOne
    @NotNull
    @JoinColumn(name = "address_id")
    private Address addressId;

}
