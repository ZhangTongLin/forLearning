package com.kaishengit;

import com.kaishengit.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/10/30.
 */

public class StudentTestCase extends BaseTest {

    @Autowired
    StudentService studentService;

    @Test
    public void test() {
        studentService.sayHello();
    }
}
