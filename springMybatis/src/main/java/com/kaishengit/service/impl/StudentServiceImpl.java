package com.kaishengit.service.impl;

import com.kaishengit.entity.Student;
import com.kaishengit.entity.StudentExample;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /*@Autowired
    private StudentExample studentExample;
*/
    @Override
    public Student findById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<Student> findAll() {
        return studentMapper.selectByExample(new StudentExample());
    }

    @Override
    public int insert(Student student) {

        return studentMapper.insertSelective(student);
    }
}
