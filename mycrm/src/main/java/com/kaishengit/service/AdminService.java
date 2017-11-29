package com.kaishengit.service;

import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Department;
import com.kaishengit.entity.Staff;

import java.util.List;

/**
 * @author Administrator.
 */
public interface AdminService {
    Admin verify(String adminName,String password);
    List<Staff> findAllStaff();
    List<Department> findAllDept();
    List<Staff> findByDeptId(Integer id);
}
