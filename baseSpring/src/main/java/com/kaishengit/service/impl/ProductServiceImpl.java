package com.kaishengit.service.impl;

import com.kaishengit.dao.Product;
import com.kaishengit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by Administrator on 2017/10/30.
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private Product product;


    @Override
    public void create() {
        product.create();
    }
}
