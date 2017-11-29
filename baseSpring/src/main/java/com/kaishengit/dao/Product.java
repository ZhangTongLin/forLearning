package com.kaishengit.dao;


import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/10/30.
 */

@Component
@Scope("prototype")
@Lazy
public class Product {

/*    public Product() {
        System.out.println("Product create");
    }*/
    public void create() {
        System.out.println("生产了一台电脑");
    }

}
