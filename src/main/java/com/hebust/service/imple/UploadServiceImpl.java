package com.hebust.service.imple;

import com.hebust.entity.UploadInfo;
import com.hebust.service.UploadService;
import com.hebust.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public UploadInfo upload(MultipartFile file, String uploadFilePath) throws Exception {
        if (file.isEmpty()){
            return null;
        }
        UploadInfo uploadInfo = new UploadInfo();
        String originalFileName;
        String fileName;
        originalFileName = file.getOriginalFilename();
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        fileName = UUIDUtils.getUUID() + "." + fileType;
        long fileSize = file.getSize();
        File packageFile = new File(uploadFilePath);
        if (!packageFile.exists()){
            packageFile.mkdir();
        }
        File targetFile = new File(uploadFilePath + "/" + fileName);
        file.transferTo(targetFile);
        uploadInfo.setBeginFileName(originalFileName);
        uploadInfo.setLastFileName(fileName);
        uploadInfo.setFileType(fileType);
        uploadInfo.setFileSize(Long.toString(fileSize));
        uploadInfo.setUploadUrl(targetFile.toString());
        return uploadInfo;
    }
}
