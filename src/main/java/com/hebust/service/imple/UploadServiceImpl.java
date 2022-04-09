package com.hebust.service.imple;

import com.hebust.entity.UploadInfo;
import com.hebust.service.UploadService;
import com.hebust.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {

    private static final String SERVICE_PATH = "http://localhost:8080";
    private final static String PATH = "UPLOAD";


    @Override
    public UploadInfo upload(MultipartFile file, String uploadFilePath) throws Exception {
        // 判断文件是否为空
        if (file.isEmpty()){
            return null;
        }
        UploadInfo uploadInfo = new UploadInfo();
        String originalFileName;    // 起始文件名称
        String fileName;            // 最终文件名称
        originalFileName = file.getOriginalFilename();
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".")+1);  // 获取文件类型
        fileName = UUIDUtils.getUUID() + "." + fileType;    // 通过UUID随机生成文件名称
        long fileSize = file.getSize();     // 文件尺寸大小
        File packageFile = new File(uploadFilePath);
        if (!packageFile.exists()){         // 判断路径是否存在，若路径不存在则创建路径
            packageFile.mkdir();
        }
        File targetFile = new File(uploadFilePath + "/" + fileName);    //
        file.transferTo(targetFile);
        uploadInfo.setBeginFileName(originalFileName);
        uploadInfo.setLastFileName(fileName);
        uploadInfo.setFileType(fileType);
        uploadInfo.setFileSize(Long.toString(fileSize));
        uploadInfo.setUploadUrl(targetFile.toString());
        uploadInfo.setResult("success");
        return uploadInfo;
    }
}
