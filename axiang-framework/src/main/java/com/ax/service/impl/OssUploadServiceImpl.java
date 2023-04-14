package com.ax.service.impl;

import com.ax.domain.ResponseResult;
import com.ax.enums.AppHttpCodeEnum;
import com.ax.exception.SystemException;
import com.ax.service.UploadService;
import com.ax.utils.PathUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * className: OssUploadServiceImpl
 * description:
 *
 * @author: axiang
 * date: 2023/4/10 0010 16:48
 */
@Service
@ConfigurationProperties(prefix = "oss")
@Data
@Slf4j
public class OssUploadServiceImpl implements UploadService {
    @Override
    public ResponseResult uploadImg(MultipartFile img) {

        //判断图片类型, 大小
        // 获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //对原始文件名进行判断
        if (!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg")) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        //如果判断通过上传文件到OSS
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOss(img, filePath); // 2023/4/10 aaaa.png
        return ResponseResult.okResult(url);
    }

    private String accessKey;
    private String secretKey;
    private String bucket;


    private String uploadOss(MultipartFile imgFile, String filePath) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                log.info("文件名: {}", putRet.key);
                log.info(": {}", putRet.hash);
                //System.out.println(putRet.key);
                //System.out.println(putRet.hash);
                return "http://rsw3xbn3b.hn-bkt.clouddn.com/" + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";
    }
}
