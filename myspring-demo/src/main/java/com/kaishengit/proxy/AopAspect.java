package com.kaishengit.proxy;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Administrator on 2017/10/30.
 */
public class AopAspect {

    public void beforeAspect(){
        System.out.println("前置通知1111");
    }

    public void afterReturning(Object result) {
        System.out.println("后置通知2222" + result);
    }

    public void afterThrowing(Exception ex) {

        System.out.println("异常通知" + ex);

    }

    public void after() {
        System.out.println("最终通知");
    }

    public void around(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("qianzhi");
            Object result = joinPoint.proceed();
            System.out.println("houzhi");
        } catch (Throwable throwable) {
            System.out.println("yichang");
        } finally {
            System.out.println("zuizhong");
        }
    }

}
