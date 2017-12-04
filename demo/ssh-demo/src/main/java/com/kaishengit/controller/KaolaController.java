package com.kaishengit.controller;

import com.kaishengit.pojo.Kaola;
import com.kaishengit.service.KaolaService;
import com.kaishengit.util.Page;
import com.kaishengit.util.QueryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Tonglin
 */
@Controller
@RequestMapping("/product")
public class KaolaController {

    @Autowired
    private KaolaService kaolaService;

    @GetMapping
    public String list(Model model,
                       HttpServletRequest request,
                       @RequestParam(defaultValue = "1",required = false,name = "p") Integer pageNum) {

        List<QueryList> queryList = QueryList.queryListsBuilder(request);

        Page<Kaola> kaolaList = kaolaService.findByQueryListAndPage(queryList,pageNum);

        model.addAttribute("pageList",kaolaList);
        return "list";
    }

    @GetMapping("/{id:\\d+}")
    public String show(@PathVariable Integer id,
                       Model model) {
        model.addAttribute("product",kaolaService.findById(id));
        return "show";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id,
                       Model model) {
        model.addAttribute("product",kaolaService.findById(id));
        return "edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String edit(Kaola kaola) {
        kaolaService.updateKaola(kaola);
        return "redirect:/product/"+ kaola.getId();
    }

    @GetMapping("/{id:\\d+}/delete")
    public String delete(@PathVariable Integer id) {
        kaolaService.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String addProduct() {
        return "new";
    }

    @PostMapping("/new")
    public String addProduct(Kaola kaola) {
        kaolaService.save(kaola);
        return "redirect:/product";
    }

}
