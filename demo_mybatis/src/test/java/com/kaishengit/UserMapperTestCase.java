package com.kaishengit;

import com.kaishengit.entity.Tag;
import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.GetSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public class UserMapperTestCase {

    SqlSession sqlSession;
    UserMapper userMapper;
    @Before
    public void init() {
        sqlSession = GetSqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void findDeptByIdTest() {
        System.out.println(userMapper.findDeptById(2));
    }

    @Test
    public void findByIdTest() {
        User user = userMapper.findById(1);
        System.out.println(user);
        List<Tag> tagList = user.getTagList();
        for (Tag tag : tagList) {
            System.out.println(tag);
        }
    }
}
