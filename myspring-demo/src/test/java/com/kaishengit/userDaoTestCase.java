package com.kaishengit;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import com.kaishengit.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/10/28.
 */
public class userDaoTestCase {


    @Test
    public void userDaoTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("userDao.xml");
        UserService userService = (UserServiceImpl)context.getBean("userService");
        //UserService userService1 = (UserServiceImpl)context.getBean("userService");
       // UserDao userDao = (UserDao)context.getBean("userDao");

        //System.out.println(userService == userService1);
        userService.save();

    }

}
