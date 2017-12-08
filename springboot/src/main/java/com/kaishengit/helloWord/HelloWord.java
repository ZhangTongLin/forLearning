package com.kaishengit.helloWord;

import com.kaishengit.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tonglin
 */
@Controller
public class HelloWord {

    @GetMapping("/hello")
    public String helloWord() {
        //User user = new User(1,"jack","北京");

        //model.addAttribute("user",user);
        //model.addAttribute("name","jack");

//        List<User> users =  Arrays.asList(new User(1,"tom","上海"),
//                new User(3,"lucy","郑州"));
//        model.addAttribute("users",users);
//        session.setAttribute("sessionMessage","session message");
        return "hello";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
