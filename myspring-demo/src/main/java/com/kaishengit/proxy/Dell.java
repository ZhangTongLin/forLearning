package com.kaishengit.proxy;

/**
 * Created by Administrator on 2017/10/29.
 */
public class Dell implements Sales {
    @Override
    public void sale() {
        System.out.println("戴尔卖出一台电脑");
    }
}
