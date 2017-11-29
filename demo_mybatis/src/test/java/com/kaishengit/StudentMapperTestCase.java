package com.kaishengit;

import com.kaishengit.entity.Student;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24.
 */
public class StudentMapperTestCase {

    SqlSession sqlSession;
    StudentMapper studentMapper;
    @Before
    public void init(){
        sqlSession = GetSqlSessionFactory.openSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }
    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void insertTest(){
        Student student = new Student();
        student.setStuName("王五");
        student.setStuTel("123456");
        studentMapper.createStudent(student);
        sqlSession.commit();
    }

    @Test
    public void deleteTest(){
        studentMapper.deleteStudentById(2);
        sqlSession.commit();
    }

    @Test
    public void updateTest(){
        Student student = new Student();
        student.setStuId(1);
        student.setStuTel("10010");
        student.setStuName("狗蛋");
        studentMapper.updateStudent(student);
        sqlSession.commit();
    }

    @Test
    public void selectStudentById(){
        Student student = studentMapper.findStudentById(2);
        System.out.println(student);
    }

    @Test
    public void selectStudents(){
        List<Student> students = studentMapper.findAllStudent();
        for (Student student : students){
            System.out.println(student);
        }
    }

    @Test
    public void pageTest(){
       /* Map<String,Integer> map = new HashMap<>();
        map.put("offset",0);
        map.put("size",3);*/

        List<Student> students = studentMapper.page(0,3);
        for (Student student : students){
            System.out.println(student);
        }
    }
}
