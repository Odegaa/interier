package com.example.webservice.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentContentDto {
    private byte[] bytes;
    private Long attachmentId;
}