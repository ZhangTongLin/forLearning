package com.kaishengit.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Tonglin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataTest {

    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    @Test
    public void setTest() {
        redisTemplate.opsForValue().set("dateName","spring");

    }

    @Test
    public void getTest() {
        String str = redisTemplate.opsForValue().get("dateName");
        System.out.println(str);
    }

    @Test
    public void setList() {
        redisTemplate.opsForList().leftPushAll("user:109:address","上海","北京");
    }

    @Test
    public void getList() {
        List<String> result = redisTemplate.opsForList().range("user:109:address",0,-1);
        for (String str : result) {
            System.out.println(str);
        }
    }

}
