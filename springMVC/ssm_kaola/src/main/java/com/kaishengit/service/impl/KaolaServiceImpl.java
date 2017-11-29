package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.KaolaType;
import com.kaishengit.entity.KaolaTypeExample;
import com.kaishengit.mapper.KaolaMapper;
import com.kaishengit.mapper.KaolaTypeMapper;
import com.kaishengit.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator.
 */
@Service
public class KaolaServiceImpl implements KaolaService {

    @Autowired
    private KaolaMapper kaolaMapper;
    @Autowired
    private KaolaTypeMapper kaolaTypeMapper;

    @Override
    public PageInfo<Kaola> findAll(Integer pageNo, Map<String,Object> params) {
        PageHelper.startPage(pageNo,10);
        List<Kaola> kaolaList = kaolaMapper.selectAllWithType(params);
        PageInfo<Kaola> pageInfo = new PageInfo<>(kaolaList);
        return pageInfo;
    }

    @Override
    public List<KaolaType> findAllType() {
        return kaolaTypeMapper.selectByExample(new KaolaTypeExample());
    }

    @Override
    public List<String> findAllPlace() {
        return kaolaMapper.selectAllPlace();
    }
}
