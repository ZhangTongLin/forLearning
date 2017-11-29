package com.kaishengit.service;

import com.kaishengit.entity.Staff;

/**
 * @author Administrator.
 */
public interface StaffService {
    Staff verify(String staffName, String password);
    void updatePassword(String password);
}
