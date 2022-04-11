package com.hebust.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 上传结果
     */
    private String result;

    /**
     * 初始文件名
     */
    private String beginFileName;

    /**
     * 最终上传文件名
     */
    private String lastFileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件上传地址
     */
    private String uploadUrl;
}
