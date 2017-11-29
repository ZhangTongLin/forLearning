package com.kaishengit.service.impl;

import com.kaishengit.dao.StudentDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.Student;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

/**
 * Created by Administrator on 2017/10/30.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentDao studentDao;


    @Override
    public void save(Student student) {
        studentDao.saveStudent(student);
      /*  if (1==1) {
            throw new RuntimeException();
        }*/
        studentDao.saveStudent(student);
    }
}
