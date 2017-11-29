package com.kaishengit.user;

import com.kaishengit.ApplicationContext;
import com.kaishengit.entity.Student;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/10/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")//(classes = ApplicationContext.class)
public class UserDITestCase {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        Student student = new Student();
        student.setStuName("jarry");
        student.setStuTel("123456");
        userService.save(student);
        System.out.println(userService.getClass().getName());
    }

}
