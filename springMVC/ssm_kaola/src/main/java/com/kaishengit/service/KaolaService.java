package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.KaolaType;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator.
 */
public interface KaolaService {
    PageInfo<Kaola> findAll(Integer pageNo, Map<String ,Object> params);
    List<KaolaType> findAllType();
    List<String> findAllPlace();
}
