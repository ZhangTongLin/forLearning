package com.kaishengit;

import com.kaishengit.service.ProductService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * @author Tonglin
 */
public class Cosumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-consumer.xml");

        context.start();

        ProductService productService = (ProductService) context.getBean("rpcProductConsumer");

        List<String> list = productService.findAllProductName();

        for (String str : list) {
            System.out.println(str);
        }

        System.in.read();

    }

}
