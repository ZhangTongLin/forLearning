package com.kaishengit;

import com.kaishengit.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/10/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)//(locations = "classpath:applicationContext.xml")
public class ProductServiceTestCase {

    @Autowired
    ProductService productService;

    @Test
    public void test() {

        productService.create();

    }



}
