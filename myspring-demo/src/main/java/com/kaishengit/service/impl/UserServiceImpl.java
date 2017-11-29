package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/10/28.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("save..........");
    }

    @Override
    public int count() {
       /* if (1 == 1) {
            throw new RuntimeException("故意异常");
        }*/
        return 100;
    }



/*    private UserDao userDao;



    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }*/
}
