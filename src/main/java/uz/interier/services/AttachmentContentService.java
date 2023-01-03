package uz.interier.services;

import uz.interier.utils.ApiResponse;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentContentService {

    ApiResponse addAttachmentContent(MultipartHttpServletRequest request);

    void getAttachmentContent(Long attachmentContentId, HttpServletResponse response);

    ApiResponse deleteAttachmentContentById(Long attachmentContentId);

}