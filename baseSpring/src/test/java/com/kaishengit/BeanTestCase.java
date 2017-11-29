package com.kaishengit;

import com.kaishengit.dao.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/10/30.
 */
public class BeanTestCase {


    @Test
    public void createTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Product product = (Product) context.getBean("product");
        Product product1 = (Product) context.getBean("product");
        System.out.println(product == product1);
        product.create();
    }

}
