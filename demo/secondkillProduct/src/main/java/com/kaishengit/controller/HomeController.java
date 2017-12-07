package com.kaishengit.controller;

import com.kaishengit.entity.Product;
import com.kaishengit.service.ProductService;
import com.kaishengit.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author Tonglin
 */
@Controller
@RequestMapping("/product")
public class HomeController {

    @Autowired
    private ProductService productService;

    /**
     * 秒杀页面
     * @param model
     * @return
     */
    @GetMapping
    public String toKillPage(Model model) {

        model.addAttribute("productList",productService.findAllProduct());
        return "home";
    }

    /**
     * 新增页面
     * @return
     */
    @GetMapping("/new")
    public String addProduct() {
        return "new";
    }

    /**
     * 新增秒杀商品
     * @param product
     * @param sTime
     * @param eTime
     * @param image
     * @return
     * @throws IOException
     */
    @PostMapping("/new")
    public String addProduct(Product product,
                             String sTime,
                             String eTime,
                             MultipartFile image) throws IOException {


        if (image == null && image.isEmpty()) {

        } else {
            InputStream imageInputStream = image.getInputStream();
            productService.addProduct(product,sTime,eTime,imageInputStream);
        }

        return "redirect:/product";
    }

    /**
     * 显示商品详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String showProductDetail(@PathVariable Integer id,
                                    Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product",product);
        return "product";
    }

    /**
     * 秒杀商品
     * @param id
     * @return
     */
    @GetMapping("/killProduct/{id:\\d+}")
    @ResponseBody
    public AjaxResult secondKillProduct(@PathVariable Integer id) {
        try {
            productService.killProductById(id);
            return AjaxResult.success();
        } catch (RuntimeException ex) {
            return AjaxResult.error(ex.getMessage());
        }
    }

}
