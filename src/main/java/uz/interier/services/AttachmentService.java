package uz.interier.services;

import uz.interier.models.Attachment;
import uz.interier.utils.ApiResponse;

import java.util.List;

public interface AttachmentService {

    List<Attachment> getAllAttachments();

    Attachment getAttachmentById(Long attachmentId);

    ApiResponse addAttachment(Attachment attachment);

    ApiResponse updateAttachment(Long attachmentId, Attachment attachment);

    ApiResponse deleteAttachment(Long attachmentId);

}