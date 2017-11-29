package com.kaishengit.controller;

import com.kaishengit.entity.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/2.
 */
@Controller
public class HelloController {

   // @RequestMapping(value = "/hello", method = {RequestMethod.GET ,RequestMethod.PUT} )
    @GetMapping("/hello")
    @PostMapping
    public String hello() {
        System.out.println("hello,SpringMVC");
        return "hello";
    }

   /* @GetMapping("/send")
    public ModelAndView Hi() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/hello");
        modelAndView.addObject("message","hello,ModelAndView");
        return modelAndView;
    }*/
/*    public String  hi(Model model) {
        model.addAttribute("message","hello");
        return "hello";
    }*/

    @GetMapping("/send")
    public String send(Integer id,@RequestParam(name = "name1",required = false,defaultValue = "jack") String name) {
        System.out.println(id);
        System.out.println(name);
        return "hello";
    }

    @GetMapping("/send1/{id:\\d+}")
    public String send1(@PathVariable Integer id) {
        System.out.println(id);
        return "hello";
    }

    @GetMapping("/save")
    public String save() {

        return "save";
    }

    @PostMapping("/save")
    public String save(User user, MultipartFile photo,
                       RedirectAttributes redirectAttributes) {
        System.out.println("name ...." + user.getUserName() + "  password ..." + user.getPassword());
        if (!photo.isEmpty()) {
            System.out.println(photo.getName());
            System.out.println(photo.getSize());
            System.out.println(photo.getOriginalFilename());
            System.out.println(photo.getContentType());

            try {
                IOUtils.copy(photo.getInputStream(),new FileOutputStream("e:/upload/" + photo.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        redirectAttributes.addFlashAttribute("message","上传成功");
        return "redirect:/save";
    }

}
