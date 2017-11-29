package com.kaishengit.mapper;

import com.kaishengit.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/23.
 */
public interface StudentMapper {
    void createStudent(Student student);
    @Delete("delete from t_stu where stu_id = #{id}")
    void deleteStudentById(int id);
    void updateStudent(Student student);
    Student findStudentById(int id);
    List<Student> findAllStudent();
    List<Student> page(@Param("offset") int offset,@Param("size") int size);
}
