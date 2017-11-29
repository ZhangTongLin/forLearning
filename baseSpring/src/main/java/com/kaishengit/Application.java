package com.kaishengit;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.StudentService;
import com.kaishengit.service.impl.StudentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Administrator on 2017/10/30.
 */

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Application {

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public StudentService studentService() {
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setUserDao(userDao());
        return studentService;
    }

}
