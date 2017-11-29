package com.kaishengit.mapper;

import com.kaishengit.entity.User;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface UserMapper {
    User findDeptById(int id);
    User findById(int userId);
}
