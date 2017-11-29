package com.kaishengit;

import com.kaishengit.entity.Student;
import com.kaishengit.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23.
 */
public class MyBatisTestCase {
    @Test
    public void insertTest() {
        SqlSession sqlSession = GetSqlSessionFactory.openSession();
       /* Student student = new Student();
        student.setStuName("jack");
        student.setStuTel("12345");*/
        sqlSession.insert("com.kaishengit.mapper.StudentMapper.createStudent",4);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void deleteTest(){
        SqlSession sqlSession = GetSqlSessionFactory.openSession();
        sqlSession.delete("com.kaishengit.mapper.StudentMapper.deleteStudentById",4);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void selectTest() {
        SqlSession sqlSession = GetSqlSessionFactory.openSession();
        List<Student> students = sqlSession.selectList("com.kaishengit.mapper.StudentMapper.findAllStudent");
        for (Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }
    @Test
    public void selectStudentTest(){
        SqlSession sqlSession = GetSqlSessionFactory.openSession();
        Student student = sqlSession.selectOne("com.kaishengit.mapper.StudentMapper.findStudentById",6);
        System.out.println(student);
        sqlSession.close();
    }
    @Test
    public void updateStudent(){
        SqlSession sqlSession = GetSqlSessionFactory.openSession();
        Student student = new Student();
        student.setStuId(5);
        student.setStuName("张三");
        student.setStuTel("100001");
        sqlSession.update("com.kaishengit.mapper.StudentMapper.updateStudent",student);
        sqlSession.commit();
        sqlSession.close();
    }
}
