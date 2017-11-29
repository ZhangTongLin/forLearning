package com.kaishengit;

import com.kaishengit.dao.UserDao;
import com.kaishengit.proxy.cglib.MyMethodInteceptor;
import com.kaishengit.service.UserService;
import com.kaishengit.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

/**
 * Created by Administrator on 2017/10/29.
 */
public class MethodInterceptorTestCase {


    @Test
    public void cglibTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);

        enhancer.setCallback(new MyMethodInteceptor());

        UserDao userDao = (UserDao) enhancer.create();

        userDao.save();

    }


}
