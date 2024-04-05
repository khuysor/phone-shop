package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.entity.AttachmentFile;
import com.huysor.ecommerce.phoneshop.repository.AttachmentRepository;
import com.huysor.ecommerce.phoneshop.services.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AttachmentImplement implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public AttachmentFile upload(MultipartFile file) throws Exception {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        String path = System.getProperty("user.dir")+"/download/";

        try{
            String fileDir = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            File dir = new File(path);
            System.out.println(dir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Save uploaded file to the directory
            Path uploadedFile = Paths.get(path + fileDir);
            Files.write(uploadedFile, file.getBytes());
            if(fileName.contains("..")){
                throw new Exception("File name contain invalid path sequence"+fileName);
            }
            AttachmentFile file1 = AttachmentFile.builder()
                    .name(fileName)
                    .type(file.getContentType())
                    .fileData(file.getBytes())
                    .build();
            return attachmentRepository.save(file1);
        }catch (Exception e){
            throw new Exception("Could not save file"+fileName);
        }

    }

    @Override
    public AttachmentFile getFileUpload(String name) throws Exception {
        return attachmentRepository.findByName(name).orElseThrow(()->new Exception("File not found with file name"+name));
    }
}
