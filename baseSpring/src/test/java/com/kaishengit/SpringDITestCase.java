package com.kaishengit;

import com.kaishengit.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/10/30.
 */
public class SpringDITestCase {


    @Test
    public void createTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductService productService = (ProductService) context.getBean("productServiceImpl");
        productService.create();
    }
}
