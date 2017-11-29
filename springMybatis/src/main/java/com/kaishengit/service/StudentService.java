package com.kaishengit.service;

import com.kaishengit.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public interface StudentService {
    Student findById(Integer id);
    List<Student> findAll();
    int insert(Student student);
}
