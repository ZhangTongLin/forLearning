package com.kaishengit.helloWord;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.entity.Movie;
import com.kaishengit.entity.User;
import com.kaishengit.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tonglin
 */
@Controller
public class HelloWord {

    @Autowired
    private MovieDao movieDao;
    @Autowired
    private MovieMapper movieMapper;

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


    @GetMapping("/layout")
    public String layout() {
        return "index";
    }

    @GetMapping("/save")
    @ResponseBody
    public String save() {
        movieMapper.save(new Movie("快餐车","成龙"));
        return "hi";
    }
}
