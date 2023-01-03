package com.example.webservice.controllers;

import com.example.webservice.models.Attachment;
import com.example.webservice.services.AttachmentService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/attachment")
public class AttachmentController {

    private final AttachmentService service;

    @Autowired
    public AttachmentController(AttachmentService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addAttachment(@Valid @RequestBody Attachment attachment) {
        ApiResponse apiResponse = service.addAttachment(attachment);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(attachment);
    }

    @GetMapping
    public ResponseEntity<List<Attachment>> getAllAttachments() {
        return ResponseEntity.ok(service.getAllAttachments());
    }

    @GetMapping("/{attachmentId}")
    public ResponseEntity<Attachment> getAllAttachments(@PathVariable Long attachmentId) {
        return ResponseEntity.ok(service.getAttachmentById(attachmentId));
    }

    @PutMapping("/{attachmentId}")
    public HttpEntity<?> updateAttachment(@PathVariable Long attachmentId,
                                          @Valid @RequestBody Attachment attachment) {
        ApiResponse apiResponse = service.updateAttachment(attachmentId, attachment);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{attachmentId}")
    public HttpEntity<?> deleteAttachmentById(@PathVariable Long attachmentId) {
        ApiResponse apiResponse = service.deleteAttachment(attachmentId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }
}