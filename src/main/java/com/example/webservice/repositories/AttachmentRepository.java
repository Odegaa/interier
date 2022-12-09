package com.example.webservice.repositories;

import com.example.webservice.models.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    boolean existsAttachmentByGenerationName(String generationName);
}