package com.practice.filemngt.service;

import com.practice.filemngt.entity.Attachment;
import com.practice.filemngt.repository.IAttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements IAttachmentService {

    public IAttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(IAttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")){
                throw new Exception("File Contains Invalid path sequence " + fileName);
            }
            Attachment attachment = new
                    Attachment( fileName, file.getContentType(), file.getBytes());

            return attachmentRepository.save(attachment);
        } catch (Exception e){
            throw new Exception("Could Not save file " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId)
                .orElseThrow(() -> new Exception("File not Found"));
    }

}
