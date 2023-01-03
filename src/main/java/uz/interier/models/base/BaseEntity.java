package uz.interier.models.base;

import uz.interier.models.templates.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private LocalDate createAt;

    @LastModifiedDate
    @Column(name = "updated")
    private LocalDate updateAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}