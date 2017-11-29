package com.kaishengit.service.impl;

import com.kaishengit.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/30.
 */

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("保存成功");
    }

    @Override
    public int update() {
        return 100;
    }
}
