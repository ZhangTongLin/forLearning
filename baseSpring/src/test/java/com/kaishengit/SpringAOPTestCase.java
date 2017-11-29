package com.kaishengit;

import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/10/30.
 */
public class SpringAOPTestCase {

    @Test
    public void saveTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class); //new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userServiceImpl");
        userService.save();
        //userService.update();
    }

}
