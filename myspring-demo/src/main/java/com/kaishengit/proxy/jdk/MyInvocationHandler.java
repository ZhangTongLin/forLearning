package com.kaishengit.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/10/29.
 */
public class MyInvocationHandler implements InvocationHandler {


    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置通知");
        System.currentTimeMillis();
        Object result = method.invoke(target,args);
        System.out.println("后置通知");
        return result;
    }
}
