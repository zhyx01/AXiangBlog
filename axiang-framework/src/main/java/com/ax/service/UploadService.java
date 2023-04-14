package com.ax.service;

import com.ax.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

public interface UploadService {

    /**
     * description: 上传头像
     * @param img: 图片
     * @return: ResponseResult <br>
     * date: 2023/4/10 0010 <br>
     */
    ResponseResult uploadImg(MultipartFile img);
}
