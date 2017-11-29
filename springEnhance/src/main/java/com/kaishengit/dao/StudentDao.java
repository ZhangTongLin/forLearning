package com.kaishengit.dao;

import com.kaishengit.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

@Repository
public class StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Student findStudentById(Integer id) {
        String sql = "select * from t_stu where stu_id = ?";
        return jdbcTemplate.queryForObject(sql, /*new StudentMapper()*//*new SingleColumnRowMapper<>(Student.class)*/new BeanPropertyRowMapper<>(Student.class), id);
    }

    public List<Student> findAllStudent() {
        String sql = "select * from t_stu";
        return jdbcTemplate.query(sql, new StudentMapper()/*new BeanPropertyRowMapper<>(Student.class)*/);
    }

    public void saveStudent(Student student) {
        String sql = "insert into t_stu (stu_name,stu_tel) values (?,?)";
        jdbcTemplate.update(sql,student.getStuName(),student.getStuTel());
    }

    public void updateStudent(Student student) {
        String sql = "update t_stu set stu_name = ?, stu_tel = ? where stu_id = ?";
        jdbcTemplate.update(sql,student.getStuName(),student.getStuTel(),student.getStuId());
    }
    public  void deleteStudent(Integer id) {
        String sql = "delete from t_stu where stu_id = ?";
        jdbcTemplate.update(sql,id);

    }

    public Long count() {
        String sql = "select count(*) from t_stu";
        return jdbcTemplate.queryForObject(sql,Long.class);
    }

    private class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setStuId(resultSet.getInt("stu_id"));
            student.setStuName(resultSet.getString("stu_name"));
            student.setStuTel(resultSet.getString("stu_tel"));
            return student;
        }
    }

}
