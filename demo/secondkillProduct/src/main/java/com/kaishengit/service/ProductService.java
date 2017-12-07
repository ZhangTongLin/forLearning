package com.kaishengit.service;

import com.kaishengit.entity.Product;

import java.io.InputStream;
import java.util.List;

/**
 * @author Tonglin
 */
public interface ProductService {


    /**
     * 保存一个新的促销商品
     * @param product
     * @param stime
     * @param eTime
     * @param imageInputStream
     */
    void addProduct(Product product, String stime, String eTime, InputStream imageInputStream);

    /**
     * 查询所有秒杀商品
     * @return
     */
    List<Product> findAllProduct();

    /**
     * 根据id查询商品详情
     * @param id
     * @return
     */
    Product findProductById(Integer id);

    /**
     * 根据id进行商品的秒杀
     * @param id
     */
    void killProductById(Integer id);
}
