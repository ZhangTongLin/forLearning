package com.kaishengit;

import com.kaishengit.entity.Kaola;
import com.kaishengit.mapper.KaolaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Administrator.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class mapperTest {

    @Autowired
    private KaolaMapper kaolaMapper;

    @Test
    public void test() {
        Kaola kaola = kaolaMapper.selectByPrimaryKey(2222);
        System.out.println(kaola.getProductName());
    }

}
