package com.kaishengit.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/10/29.
 */
public class MyMethodInteceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
        System.out.println("....");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("//////");
        return result;
    }
}
