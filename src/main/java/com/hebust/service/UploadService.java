package com.hebust.service;

import com.hebust.entity.UploadInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    UploadInfo upload(MultipartFile file, String uploadFilePath) throws Exception;
}
