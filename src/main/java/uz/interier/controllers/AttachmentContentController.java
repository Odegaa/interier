package com.example.webservice.controllers;

import com.example.webservice.services.AttachmentContentService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/attachment/content")
public class AttachmentContentController {

    private final AttachmentContentService service;

    @Autowired
    public AttachmentContentController(AttachmentContentService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addAttachmentContent(MultipartHttpServletRequest request) {
        ApiResponse apiResponse = service.addAttachmentContent(request);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping("/{attachmentContentId}")
    public void getAttachmentContent(@PathVariable Long attachmentContentId,
                                     HttpServletResponse response) {
        service.getAttachmentContent(attachmentContentId, response);
    }

    @DeleteMapping("/{attachmentContentId}")
    public HttpEntity<?> deleteAttachmentContentById(@PathVariable Long attachmentContentId) {
        ApiResponse apiResponse = service.deleteAttachmentContentById(attachmentContentId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

}