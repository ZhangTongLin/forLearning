package com.kaishengit.controller;

import com.kaishengit.entity.Department;
import com.kaishengit.entity.Staff;
import com.kaishengit.service.AdminService;
import com.kaishengit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Administrator.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/page")
    public String adminPage(Model model) {
        model.addAttribute("deptList",adminService.findAllDept());
        return "admin/page";
    }

    @GetMapping("/staff/list")
    public List<Staff> showStaff(Integer deptId) {
        System.out.println(deptId);
        if (deptId == 1) {
            return adminService.findAllStaff();
        } else {
            return adminService.findByDeptId(deptId);
        }
    }
}
