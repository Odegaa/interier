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
@Table(name = "attachment_content")
public class AttachmentContent extends BaseEntity {

    @NotNull
    @Column(name = "bytes")
    private byte[] bytes;

    @OneToOne
    @NotNull
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

}
