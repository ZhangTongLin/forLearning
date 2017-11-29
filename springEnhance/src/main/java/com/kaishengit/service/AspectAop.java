package com.kaishengit.service;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/10/30.
 */

@Component
@Aspect
public class AspectAop {

    @Pointcut("execution(* com.kaishengit.service..*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before() {
        System.out.println("前置通知");
    }

    @AfterReturning(pointcut = "pointcut()",returning = "result")
    public void afterReturning(Object result) {
        System.out.println("后置通知" + result);
    }

    @AfterThrowing(throwing = "ex",pointcut = "pointcut()")
    public void afterThrowing(Exception ex) {
        System.out.println("异常通知");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("最终通知");
    }

   // @Around("pointcut()")
    public void around() {

    }

}
