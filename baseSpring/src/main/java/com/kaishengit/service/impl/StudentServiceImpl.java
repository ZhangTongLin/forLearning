package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/30.
 */

public class StudentServiceImpl implements StudentService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void sayHello() {
        userDao.sayHello();
    }
}
