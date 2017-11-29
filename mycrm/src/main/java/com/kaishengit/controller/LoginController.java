package com.kaishengit.controller;

import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Staff;
import com.kaishengit.service.AdminService;
import com.kaishengit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Administrator.
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private StaffService staffService;
    @Autowired
    private AdminService adminService;


    @GetMapping
    public String staffLogin() {
        return "staff/login";
    }

    @PostMapping
    public String staffLogin(String userName, String password,
                             RedirectAttributes redirectAttributes) {

        Staff staff = staffService.verify(userName,password);

        if (staff != null) {
            return "redirect:/staff/page";
        } else {

            Admin admin = adminService.verify(userName,password);

            if (admin != null) {

                return "redirect:/admin/page";

            } else {

                redirectAttributes.addFlashAttribute("error","账户或者密码错误");
                return "redirect:login";

            }

        }
    }

}
