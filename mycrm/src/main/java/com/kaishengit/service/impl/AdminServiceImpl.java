package com.kaishengit.service.impl;

import com.kaishengit.entity.*;
import com.kaishengit.mapper.AdminMapper;
import com.kaishengit.mapper.DepartmentMapper;
import com.kaishengit.mapper.StaffMapper;
import com.kaishengit.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Admin verify(String adminName, String password) {
        password = DigestUtils.md5Hex(password + "#&@!,.^*&(*&)(*)");
        return adminMapper.verify(adminName,password);
    }

    @Override
    public List<Staff> findAllStaff() {
        return staffMapper.selectByExample(new StaffExample());
    }

    @Override
    public List<Department> findAllDept() {
        return departmentMapper.selectByExample(new DepartmentExample());
    }

    @Override
    public List<Staff> findByDeptId(Integer id) {
        return staffMapper.findByDeptId(id);
    }

}
