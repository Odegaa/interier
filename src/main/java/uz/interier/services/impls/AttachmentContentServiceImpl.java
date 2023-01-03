package uz.interier.services.impls;

import uz.interier.models.Attachment;
import uz.interier.models.AttachmentContent;
import uz.interier.repositories.AttachmentContentRepository;
import uz.interier.repositories.AttachmentRepository;
import uz.interier.services.AttachmentContentService;
import uz.interier.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentContentServiceImpl implements AttachmentContentService {

    private final AttachmentContentRepository repository;
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentContentServiceImpl(AttachmentContentRepository repository,
                                        AttachmentRepository attachmentRepository) {
        this.repository = repository;
        this.attachmentRepository = attachmentRepository;
    }


    @Override
    public ApiResponse addAttachmentContent(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            String nameForSystem = UUID.randomUUID().toString();

            Attachment attachment = new Attachment(originalFilename, contentType, size, nameForSystem);

            assert originalFilename != null;
            nameForSystem += originalFilename.substring(originalFilename.lastIndexOf("."));
            attachmentRepository.save(attachment);

            try {
                byte[] bytes = file.getBytes();
                AttachmentContent attachmentContent = new AttachmentContent(bytes, attachment);
                repository.save(attachmentContent);

                Path path = Paths.get("files/", nameForSystem);
                Files.copy(file.getInputStream(), path);
                return new ApiResponse("Attachment Content successfully SAVED!", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ApiResponse("An error occurred", false);
    }

    @Override
    public void getAttachmentContent(Long attachmentContentId, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentContentId);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalAttachmentContent = repository.findById(attachment.getId());

            if (optionalAttachmentContent.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                byte[] bytes = attachmentContent.getBytes();

                response.setHeader("Content-Disposition",
                        "attachment; filename =\"" + attachment.getOriginalName() + "\"");
                response.setContentType(attachment.getContentType());
                try {
                    FileCopyUtils.copy(bytes, response.getOutputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ApiResponse deleteAttachmentContentById(Long attachmentContentId) {
        Optional<AttachmentContent> optionalAttachmentContent = repository.findById(attachmentContentId);
        if (optionalAttachmentContent.isPresent()) {
            repository.deleteById(attachmentContentId);
            return new ApiResponse("Attachment content deleted!", true);
        }
        return new ApiResponse("Content not found!", false);
    }
}