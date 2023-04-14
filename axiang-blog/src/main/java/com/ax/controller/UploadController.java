package com.ax.controller;

import com.ax.domain.ResponseResult;
import com.ax.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * className: UploadController
 * description: 上传文件
 *
 * @author: axiang
 * date: 2023/4/10 0010 16:44
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * description: 上传头像
     * @param img: 头像图片
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}
