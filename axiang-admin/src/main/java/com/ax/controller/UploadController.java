package com.ax.controller;

import com.ax.annotation.SystemLog;
import com.ax.domain.ResponseResult;
import com.ax.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * className: UploadController
 * description:
 *
 * @author: axiang
 * date: 2023/4/12 0012 15:43
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * description: 写博文上传图片
     * @param multipartFile: 图片
     * @return: ResponseResult <br>
     * date: 2023/4/12 0012 <br>
     */
    @PostMapping("/upload")
    @SystemLog(businessName = "后台写博文上传文件")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile) {

        try {
            return uploadService.uploadImg(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }
}
