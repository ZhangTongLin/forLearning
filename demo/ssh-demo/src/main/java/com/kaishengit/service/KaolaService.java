package com.kaishengit.service;

import com.kaishengit.dao.KaolaDao;
import com.kaishengit.pojo.Kaola;
import com.kaishengit.util.Page;
import com.kaishengit.util.QueryList;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Tonglin
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class KaolaService {

    @Autowired
    private KaolaDao kaolaDao;


    public List<Kaola> findAll() {

        return kaolaDao.findAll();

    }

    public Kaola findById(Integer id) {
        return kaolaDao.findById(id);
    }

    public void updateKaola(Kaola kaola) {
        kaolaDao.saveOrUpdate(kaola);
    }

    public void deleteById(Integer id) {
        kaolaDao.deleteById(id);
    }

    public void save(Kaola kaola) {
        kaolaDao.saveOrUpdate(kaola);
    }

    public List<Kaola> findByQueryList(List<QueryList> queryList) {
        return kaolaDao.findByQuery(queryList);
    }

    public Page<Kaola> findByQueryListAndPage(List<QueryList> queryList, Integer pageNum) {
        return kaolaDao.queryForPage(queryList,pageNum);
    }
}
