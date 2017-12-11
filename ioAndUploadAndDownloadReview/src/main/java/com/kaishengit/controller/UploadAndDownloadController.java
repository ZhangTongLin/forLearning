package com.kaishengit.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @author Tonglin
 */
@Controller
public class UploadAndDownloadController {


    @GetMapping("/upload")
    public String toUpload() {
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(Model model,
                         MultipartFile file) {

        String newName = "";

        try {
            InputStream inputStream = file.getInputStream();

            String name = file.getOriginalFilename();
            String suff = name.substring(name.lastIndexOf("."));
            newName = UUID.randomUUID().toString() + suff;

            OutputStream outputStream = new FileOutputStream(new File("e:/upload",newName));

            System.out.println("NAME:>>" + name + ">>>newName>>" + newName);

            /*byte[] bytes = new byte[100];

            while (inputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }*/

            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/sh?name="+newName;
    }

    @GetMapping("/sh")
    public String showAndDownload(Model model,
                                  String name) {
        //String name = "f6c5f185-1c8b-4a53-9616-362bab8976dd.png";
        model.addAttribute("name",name);
        return "show";
    }

    @GetMapping("/download")
    public void showOrDownload(@RequestParam(required = false) String newName,
                                 HttpServletResponse response,
                                 @RequestParam(required = false) Integer id) {
        try {

            OutputStream outputStream = response.getOutputStream();

            InputStream inputStream =  inputStream = new FileInputStream(new File("e:/upload",newName));

            if (id == null) {



                response.setContentType("application/octet-stream");

                String fileName = new String(newName.getBytes("UTF-8"),"ISO8859-1");
                response.addHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");


            }

            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
