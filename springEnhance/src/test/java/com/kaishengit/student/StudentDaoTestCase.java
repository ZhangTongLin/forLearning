package com.kaishengit.student;

import com.kaishengit.ApplicationContext;
import com.kaishengit.dao.StudentDao;
import com.kaishengit.entity.Student;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)//(locations = "classpath:applicationContext.xml")//(classes = ApplicationContext.class)
public class StudentDaoTestCase {

    @Autowired
    StudentDao studentDao;
   // UserService userService;

    @Test
    public void test() {
       /* Student student = new Student();
        student.setStuName("jarry12");
        student.setStuTel("123456");
        userService.save(student);
        System.out.println(userService.getClass().getName());*/
        Student student = studentDao.findStudentById(6);
        System.out.println(student);
//        List<Student> studentList = studentDao.findAllStudent();
//        for (Student student : studentList) {
//            System.out.println(student);
//        }



       // System.out.println( studentDao.count());
//        Student student = new Student();
//        student.setStuName("jarry");
//        student.setStuTel("123456");
//     // studentDao.saveStudent(student);
//        student.setStuId(16);
//        studentDao.updateStudent(student);
         // studentDao.deleteStudent(9);
    }




}
