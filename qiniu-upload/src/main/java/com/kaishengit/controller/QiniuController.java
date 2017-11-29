package com.kaishengit.controller;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @author Administrator.
 */
@Controller
public class QiniuController {

    @GetMapping("/upload")
    public String qiuniu(Model model) {


        String accessKey = "pvKnu_iHBI2zvxi3SHRf8ELZh8_zC34wrFMe1Lnk";
        String secretKey = "KLbKh0D8eOVUEHLvKqQyvRERKhvW4hHJSMxdZy1t";
        String bucket = "myupload";
        Auth auth = Auth.create(accessKey, secretKey);
        //String upToken = auth.uploadToken(bucket);
        //System.out.println(upToken);

        StringMap putPolicy = new StringMap();
        putPolicy.put("returnUrl", "http://localhost:8080/upload/callback");
        //putPolicy.put("returnBody","{\"key\" : \"fileKey\"}");//重新设置返回json数据

        long expireSeconds = 3600;

        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        model.addAttribute("upToken",upToken);

        return "qiniu";
    }

    @GetMapping("/upload/callback")
    public String callBack(String upload_ret) {
        String jsonString = new String(Base64.getDecoder().decode(upload_ret));
        System.out.println(jsonString);


        return "qiniu";
    }


    @GetMapping("/upload/local")
    public String localUpload() {
        return "upload_local";
    }

    @PostMapping("/upload/local")
    public String localUpload(MultipartFile file) throws IOException {

        Configuration config = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(config);

        String accessKey = "pvKnu_iHBI2zvxi3SHRf8ELZh8_zC34wrFMe1Lnk";
        String secretKey = "KLbKh0D8eOVUEHLvKqQyvRERKhvW4hHJSMxdZy1t";
        String bucket = "myupload";

        Auth auth = Auth.create(accessKey,secretKey);
        String uptoken = auth.uploadToken(bucket);

        byte[] bytes = file.getBytes();
        Response response = uploadManager.put(bytes,null,uptoken);
        DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
        System.out.println("key>>>>>"+defaultPutRet.key);
        System.out.println("hash>>>>"+defaultPutRet.hash);

        return "/upload/local";
    }

    @GetMapping("/download")
    public String downLoad(String name, HttpServletResponse response) throws IOException {

        String domainOfBucket = "http://ozp828v10.bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(name, "utf-8");
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);

        System.out.println(finalUrl);

        InputStream inputStream = new URL(finalUrl).openStream();

//        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(finalUrl).openConnection();
//
//        InputStream inputStream = httpURLConnection.getInputStream();
        OutputStream outputStream = response.getOutputStream();

        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.flush();
        outputStream.close();

        return "redirect:/upload";


    }

}
