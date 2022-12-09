package com.example.webservice.services.impls;

import com.example.webservice.models.Attachment;
import com.example.webservice.models.templates.Status;
import com.example.webservice.repositories.AttachmentRepository;
import com.example.webservice.services.AttachmentService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository repository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Attachment> getAllAttachments() {
        return repository.findAll();
    }

    @Override
    public Attachment getAttachmentById(Long attachmentId) {
        return repository.findById(attachmentId).orElse(null);
    }

    @Override
    public ApiResponse addAttachment(Attachment attachment) {
        try {
            boolean byGenerationName = repository.existsAttachmentByGenerationName(attachment.getGenerationName());
            if (byGenerationName) {
                return new ApiResponse("This generation name already exist!", false);
            }
            Attachment newAttachment = new Attachment();
            newAttachment.setOriginalName(attachment.getOriginalName());
            newAttachment.setContentType(attachment.getContentType());
            newAttachment.setSize(attachment.getSize());
            newAttachment.setStatus(Status.ACTIVE);
            newAttachment.setGenerationName(UUID.randomUUID().toString());
            repository.save(attachment);
            return new ApiResponse("Successfully SAVED!", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("An error occurred", false);
        }
    }

    @Override
    public ApiResponse updateAttachment(Long attachmentId, Attachment attachment) {
        try {
            Optional<Attachment> optionalAttachment = repository.findById(attachmentId);
            if (!optionalAttachment.isPresent()) {
                return new ApiResponse("Attachment not found!", false);
            }
            Attachment updatingAttachment = optionalAttachment.get();
            updatingAttachment.setOriginalName(attachment.getOriginalName());
            updatingAttachment.setContentType(attachment.getContentType());
            updatingAttachment.setSize(attachment.getSize());
            updatingAttachment.setStatus(Status.ACTIVE);
            updatingAttachment.setGenerationName(UUID.randomUUID().toString());
            repository.save(updatingAttachment);
            return new ApiResponse("Attachment successfully updated!", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("An error occurred!", false);
        }
    }

    @Override
    public ApiResponse deleteAttachment(Long attachmentId) {
        Optional<Attachment> attachmentOptional = repository.findById(attachmentId);
        if (!attachmentOptional.isPresent()) {
            return new ApiResponse("Attachment not found!", false);
        }
        repository.deleteById(attachmentId);
        return new ApiResponse("Attachment deleted!", true);
    }
}