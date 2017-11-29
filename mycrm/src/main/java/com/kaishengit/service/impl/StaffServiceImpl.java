package com.kaishengit.service.impl;

import com.kaishengit.entity.Staff;
import com.kaishengit.mapper.StaffMapper;
import com.kaishengit.service.StaffService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Administrator.
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Staff verify(String staffName, String password) {

        password = DigestUtils.md5Hex(password + "#&@!,.^*&(*&)(*)");
        return staffMapper.verify(staffName,password);

    }

    @Override
    public void updatePassword(String password) {

    }
}
