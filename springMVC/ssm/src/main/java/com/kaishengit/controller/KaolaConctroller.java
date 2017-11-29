package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.entity.Kaola;
import com.kaishengit.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator.
 */
@Controller
@RequestMapping("/product")
public class KaolaConctroller {

    @Autowired
    private KaolaService kaolaService;

    @GetMapping
    public String findAll(@RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,
                          Model model,
                          @RequestParam(required = false,defaultValue = "") String productName,
                          @RequestParam(required = false,defaultValue = "") String place,
                          @RequestParam(required = false,defaultValue = "") Integer typeId) {

        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("productName",productName);
        queryParam.put("place",place);
        queryParam.put("typeId",typeId);

        PageInfo<Kaola> pageInfo = kaolaService.findAll(pageNo,queryParam);
        model.addAttribute("placeList",kaolaService.findAllPlace());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeList",kaolaService.findAllType());

        return "/product/list";
    }

    @GetMapping("/new")
    public String addProduct(Model model) {
        model.addAttribute("typeList",kaolaService.findAllType());
        return "/product/new";
    }
    @PostMapping("/new")
    public String addProduct(Kaola kaola, RedirectAttributes redirectAttributes) {
        kaolaService.save(kaola);
        redirectAttributes.addFlashAttribute("message","保存成功");
        return "redirect:/product";
    }

    @GetMapping("/{id:\\d+}")
    public String show(@PathVariable Integer id,Model model) {
        Kaola kaola = kaolaService.findById(id);
        model.addAttribute("product",kaola);
        return "/product/show";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("typeList",kaolaService.findAllType());
        model.addAttribute("product",kaolaService.findById(id));
        return "/product/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String edit(Kaola kaola,RedirectAttributes redirectAttributes) {
        kaolaService.edit(kaola);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/product/"+kaola.getId();
    }

    @GetMapping("/{id:\\d+}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
        kaolaService.delete(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/product";
    }
}
