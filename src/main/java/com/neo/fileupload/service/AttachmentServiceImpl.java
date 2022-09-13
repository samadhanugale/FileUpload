package com.neo.fileupload.service;

import com.neo.fileupload.entity.Attachment;
import com.neo.fileupload.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService{
@Autowired
    private AttachmentRepository attachmentRepository;


    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new Exception("File name contains invalid path sequence! "+fileName);
            }

            Attachment attachment = new Attachment(fileName,file.getContentType(),file.getBytes());
            return attachmentRepository.save(attachment);
        }catch (Exception e){
            throw new Exception("Could not save file! "+fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow(()->new Exception("File not found with ID : "+fileId));
    }
}
