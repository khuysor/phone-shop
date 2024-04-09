package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.entity.AttachmentFile;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface AttachmentService {
 AttachmentFile upload (MultipartFile file) throws Exception;
 AttachmentFile getFileUpload(String name) throws Exception;
Map<AttachmentFile ,Long>multiUpload(MultipartFile file);

}
