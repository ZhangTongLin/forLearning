package com.kaishengit;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/10/30.
 */
public class AopAspectTestCase {

    @Test
    public void aopTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("userDao.xml");

        UserService userService = (UserService) context.getBean("userService");

        userService.save();
        /*System.out.println(userService.getClass().getName());
        userService.count();*/
    }

}
