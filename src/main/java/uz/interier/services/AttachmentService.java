package com.example.webservice.services;

import com.example.webservice.models.Attachment;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface AttachmentService {

    List<Attachment> getAllAttachments();

    Attachment getAttachmentById(Long attachmentId);

    ApiResponse addAttachment(Attachment attachment);

    ApiResponse updateAttachment(Long attachmentId, Attachment attachment);

    ApiResponse deleteAttachment(Long attachmentId);

}