package com.kaishengit.service.impl;

import com.kaishengit.entity.Student;
import com.kaishengit.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StudentServiceImplTestCase {

    @Autowired
    private StudentService studentService;

    @Test
    public void findById() throws Exception {

        Student student = studentService.findById(6);

            System.out.println(student);
    }

    @Test
    public void findAllTest() {
        List<Student> studentList = studentService.findAll();
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void insertTest() {
        Student student = new Student();
        student.setStuName("小花");
        student.setStuTel("12313312");
        studentService.insert(student);
        int i =student.getStuId();
        System.out.println(i);
    }

}