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
@Table(name = "attachment")
public class Attachment extends BaseEntity {

    @NotNull
    @Column(unique = true, name = "original_name")
    private String originalName;

    @NotNull
    @Column(name = "content_type")
    private String contentType;

    @NotNull
    @Column(name = "size")
    private Long size;

    @NotNull
    @Column(name = "generation_name")
    private String generationName;

}
