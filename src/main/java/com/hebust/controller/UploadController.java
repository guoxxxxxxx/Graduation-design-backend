package com.hebust.controller;

import com.hebust.entity.UploadInfo;
import com.hebust.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;
    private final static String UPLOAD_AVATAR_PATH = "F:/UPLOAD/avatar";
    private final static String UPLOAD_IMG_PATH = "F:/UPLOAD/img";

    /**
     * 上传用户头像
     */
    @RequestMapping("/uploadAvatar")
    public UploadInfo uploadAvatar(@RequestParam("file")MultipartFile file){
        UploadInfo uploadInfo = null;
        try {
            uploadInfo = uploadService.upload(file, UPLOAD_AVATAR_PATH);
        }catch (Exception e){
            e.printStackTrace();
        }
        return uploadInfo;
    }

    /**
     * 上传订单图片
     */
    @RequestMapping("/uploadImg")
    public UploadInfo uploadItemImg(@RequestParam("file")MultipartFile file){
        UploadInfo uploadInfo = null;
        try {
            uploadInfo = uploadService.upload(file, UPLOAD_IMG_PATH);
        }catch (Exception e){
            e.printStackTrace();
        }
        return uploadInfo;
    }
}
