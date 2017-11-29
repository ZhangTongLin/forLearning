package com.kaishengit.controller;

import com.kaishengit.entity.Kaola;
import com.kaishengit.mapper.KaolaMapper;
import com.kaishengit.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator.
 */
@Controller
public class FindController {


    @Autowired
    private KaolaService kaolaService;

    @GetMapping("/find/{id:\\d+}")
    @ResponseBody
    public Kaola findById(@PathVariable Integer id) {
        Kaola kaola = kaolaService.findById(id);
        System.out.println(id);
        return kaola;
    }

//    @GetMapping("/page")
//    @ResponseBody
//    public List<Kaola> page(@RequestParam(required = false,name = "p",defaultValue = "1") Integer pageNo) {
//
//        /*List<Kaola> kaolaList = kaolaService.page(pageNo);*/
////        System.out.println(pageNo);
////        return kaolaList;
//
//    }

}
