package com.lordbao.bigevent.controller;


import com.google.gson.Gson;
import com.lordbao.bigevent.config.OSSConfigProperties;
import com.lordbao.bigevent.util.Result;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author Lord_Bao
 * @Date 2025/2/13 14:50
 * @Version 1.0
 */
@RestController
public class FileUploadController {

    @Autowired
    private OSSConfigProperties properties;

    @PostMapping("upload")
    public Result<String> upload(MultipartFile file) {
        // 检查文件是否为空
        if (file == null || file.isEmpty()) {
            return Result.error("请上传文件");
        }


        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);


        //生成上传凭证，然后准备上传
        String accessKey = properties.getAccessKey();
        String secretKey = properties.getSecretKey();
        String bucket = properties.getBucket();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try(InputStream inputStream= file.getInputStream()) {

            //获取文件的后缀,比如aaa.png, 此处得到的是.png
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf('.'));

            // key 是文件的命名，比如 "001.png"
            // 默认不指定 key 的情况下，以文件内容的 hash 值作为文件名
            // 此处通过UUID随机生成文件命名
            String key = UUID.randomUUID().toString()+suffix;

            //上传文件到七牛云
            Response response = uploadManager.put(inputStream,key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            //putRet.key 就是上面生成的key
            //得到图片上传的地址,并返回
            String imageUrl = properties.getDomain()+"/"+putRet.key;
            return Result.success(imageUrl);

        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
            return Result.error("上传失败: " + ex.getMessage());
        }catch (IOException ex) {
            ex.printStackTrace();
            return Result.error("上传失败: " + ex.getMessage());
        }

    }
}
