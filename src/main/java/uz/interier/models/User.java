package uz.interier.models;

import uz.interier.models.base.BaseEntity;
import uz.interier.models.templates.Roles;
import uz.interier.models.templates.Status;
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
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @OneToMany
    @JoinColumn(name = "card_id")
    @ToString.Exclude
    private List<Card> cards;

    @NotNull
    private Roles roles;

    @NotNull
    private Status status;

}
