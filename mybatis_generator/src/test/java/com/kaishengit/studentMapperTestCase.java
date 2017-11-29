package com.kaishengit;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentExample;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.utils.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27.
 */
public class studentMapperTestCase {

    SqlSession sqlSession;
    StudentMapper studentMapper;
    @Before
    public void init(){
        sqlSession = GetSqlSessionFactory.getSqlSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void saveTest(){
        Student student = new Student();
        student.setStuName("jack");
        student.setStuTel("123123");
        studentMapper.insert(student);
        sqlSession.commit();
    }

    @Test
    public void save2Test() {
        Student student = new Student();
        student.setStuName("tom");
        student.setStuTel("123123");
        studentMapper.insertSelective(student);
        System.out.println("id....." + student.getStuId());
        sqlSession.commit();
    }

    @Test
    public void selectTest(){
        Student student = studentMapper.selectByPrimaryKey(7);

        System.out.println(student);
    }

    @Test
    public void selectByExample(){
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStuTelLike("%123%").andStuNameLike("%om%");

        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void findAllTest() {
        List<Student> studentList = studentMapper.selectByExample(new StudentExample());
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void deleteByExample() {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuTelLike("%122%");
        studentMapper.deleteByExample(studentExample);
        sqlSession.commit();
    }

    @Test
    public void updateTest() {
        Student student = new Student();
        student.setStuName("lucy");
        student.setStuTel("100101");
        student.setStuId(7);
        studentMapper.updateByPrimaryKey(student);
        sqlSession.commit();
    }

    @Test
    public void updateByExampleTest() {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStuTelLike("%123%");
        Student student = new Student();
        student.setStuTel("10086");
        studentMapper.updateByExampleSelective(student,studentExample);
        sqlSession.commit();
    }

    @Test
    public void findByOrTest() {
        StudentExample studentExample = new StudentExample();
        studentExample.or().andStuNameLike("%tom%");
        studentExample.or().andStuTelLike("%100%");
        studentExample.setOrderByClause("stu_id desc");


        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void pageTest() {
       // PageHelper.startPage(2,3);
        PageHelper.offsetPage(0,3);
        StudentExample studentExample = new StudentExample();
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        for (Student student : studentList) {
            System.out.println(student);
        }

        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        System.out.println("endRow >>>>>>>>>>" + pageInfo.getEndRow());
        System.out.println("pages : " + pageInfo.getPages() + "pageSize:" + pageInfo.getPageSize() + "total:" + pageInfo.getTotal());
        for (Student student : pageInfo.getList()) {
            System.out.println("name>>>>>>>>>>>" + student.getStuName());
        }
    }

    @Test
    public void pageTest2() {

        List<Student> studentList = studentMapper.page(3,2);
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
