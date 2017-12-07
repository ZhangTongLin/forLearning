package com.kaishengit.util;

import com.google.gson.Gson;
import com.kaishengit.service.exception.ServiceException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Tonglin
 */
public class QiniuUtil {

    private static String accessKey = "pvKnu_iHBI2zvxi3SHRf8ELZh8_zC34wrFMe1Lnk";
    private static String secretKey = "KLbKh0D8eOVUEHLvKqQyvRERKhvW4hHJSMxdZy1t";
    private static String bucket = "myupload";

    public static String qiniuUpload(InputStream inputStream) throws RuntimeException {
        Configuration configuration = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(accessKey,secretKey);
        String uploadToken = auth.uploadToken(bucket);

        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            Response response = uploadManager.put(bytes,null,uploadToken);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            return defaultPutRet.key;
        } catch (IOException ex) {
            throw new ServiceException("上传到七牛异常");
        }
    }

    public static InputStream qiniuDownload(String key) throws RuntimeException {

        String domainOfBucket = "http://ozp828v10.bkt.clouddn.com";
        String finalUrl = String.format("%s/%s", domainOfBucket, key);

        try {
            InputStream inputStream= new URL(finalUrl).openStream();
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("下载异常");
        }
    }

}
