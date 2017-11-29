package com.kaishengit.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Administrator.
 */
public class ShiroTestCase {


    @Test
    public void shiroTest1() {

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager securityManager = factory.getInstance();

        //设置securityManager
        SecurityUtils.setSecurityManager(securityManager);

        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("tom","123123");

        try {
            subject.login(token);

        } catch (UnknownAccountException e) {
            System.out.println("找不到该账号");
        } catch (LockedAccountException e) {
            System.out.println("该账号被冻结");
        } catch (IncorrectCredentialsException e) {
            System.out.println("账号或者密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证异常");
        }
    }

    @Test
    public void shiroTest2() {

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("tom1","123456");

        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            System.out.println("找不到该账号");
        } catch (LockedAccountException e) {
            System.out.println("该账号被冻结");
        } catch (IncorrectCredentialsException e) {
            System.out.println("账号或者密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证异常");
        }

    }

    @Test
    public void test3() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-roles.ini");
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("tom","123321");

        try {
            subject.login(token);
            System.out.println("登录成功");
            System.out.println("has ceo" + subject.hasRole("ceo"));
            System.out.println("has ceo,admin,hr" + subject.hasAllRoles(Arrays.asList("admin","hr")));

            boolean[] booleans = subject.hasRoles(Arrays.asList("admin","ceo","hr"));

            for (boolean b : booleans) {
                System.out.println(b);
            }


            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(subject.isPermitted("admin:select"));
            System.out.println(subject.isPermitted("ceo:delete"));
            //subject.checkRole("admin:select");
        } catch (UnknownAccountException e) {
            System.out.println("该账户不存在");
        } catch (LockedAccountException e) {
            System.out.println("该账户已被冻结");
        } catch (IncorrectCredentialsException e) {
            System.out.println("账号或者密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证异常");
        }

    }

}
