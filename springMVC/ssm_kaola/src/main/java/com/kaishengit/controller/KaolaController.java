package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Administrator.
 */
@Controller
@RequestMapping("/product")
public class KaolaController {

    @Autowired
    private KaolaService kaolaService;

    @GetMapping
    public String findKaolaAll(@RequestParam(name = "p" ,required = false, defaultValue = "1") Integer pageNo,
                               Model model,
                               @RequestParam(required = false,defaultValue = "") String productName,
                               @RequestParam(required = false ,defaultValue = "") String place,
                               @RequestParam(required = false,defaultValue = "") Integer typeId) {
        Map<String,Object> params = Maps.newHashMap();
        params.put("productName",productName);
        params.put("place",place);
        params.put("typeId",typeId);

        model.addAttribute("pageInfo",kaolaService.findAll(pageNo,params));
        model.addAttribute("typeList",kaolaService.findAllType());
        model.addAttribute("place",kaolaService.findAllPlace());
        return  "product/list";
    }

}
