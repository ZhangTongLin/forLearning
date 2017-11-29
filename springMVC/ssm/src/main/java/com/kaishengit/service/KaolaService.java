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
    Kaola findById(Integer id);
    PageInfo<Kaola> findAll(Integer pageNo, Map<String,Object> quertParam);
    List<KaolaType> findAllType();
    void save(Kaola kaola);
    void edit(Kaola kaola);
    void delete(Integer id);
    List<String> findAllPlace();

}
