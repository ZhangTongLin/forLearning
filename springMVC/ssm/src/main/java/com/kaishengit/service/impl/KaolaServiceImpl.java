package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.KaolaExample;
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
    public Kaola findById(Integer id) {

        Kaola kaola = kaolaMapper.selectByPrimaryKey(id);
        KaolaType kaolaType = kaolaTypeMapper.selectByPrimaryKey(kaola.getTypeId());
        kaola.setType(kaolaType);
        return kaola;
    }

    @Override
    public PageInfo<Kaola> findAll(Integer pageNo, Map<String,Object> queryParam) {

        PageHelper.startPage(pageNo,10);
        List<Kaola> kaolaList = kaolaMapper.selectKaolaWithType(queryParam);
        return new PageInfo<Kaola>(kaolaList);
    }

    @Override
    public List<KaolaType> findAllType() {
        return kaolaTypeMapper.selectByExample(new KaolaTypeExample());
    }

    @Override
    public void save(Kaola kaola) {
        kaola.setCommentNum(0);
        kaolaMapper.insertSelective(kaola);
    }

    @Override
    public void edit(Kaola kaola) {
        kaolaMapper.updateByPrimaryKeySelective(kaola);
    }

    @Override
    public void delete(Integer id) {
        kaolaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<String> findAllPlace() {
        return kaolaMapper.findAllPlace();
    }

}
