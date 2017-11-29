package com.kaishengit.controller;

import com.kaishengit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator.
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/page")
    public String staffPage() {
        return "staff/page";
    }

    @GetMapping("/set")
    public String staffSet() {
        return "staff/set";
    }

    @PostMapping("/set")
    public String setPassword(String password) {
        staffService.updatePassword(password);
        return null;
    }
}
