package com.example.webservice.repositories;

import com.example.webservice.models.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Long> {
}