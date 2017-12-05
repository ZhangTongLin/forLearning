package com.kaishengit.redis;

import com.kaishengit.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Tonglin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringJedis {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void setTest() {
        Jedis jedis = jedisPool.getResource();

        jedis.sadd("user:103:address","beijing","上海","永城");
        jedis.close();

    }

    @Test
    public void getTest() {
        Jedis jedis =  jedisPool.getResource();

        Set<String> result =  jedis.smembers("user:103:address");

        for (String str : result) {
            System.out.println(str);
        }
        jedis.close();

    }

    @Test
    public void setHash() {
        Jedis jedis = jedisPool.getResource();

        Map<String ,String> map = new HashMap<>();
        map.put("name","jack");
        map.put("age","20");
        jedis.hmset("user:104",map);
        jedis.close();
    }

    @Test
    public void getHashTest() {
        Jedis jedis = jedisPool.getResource();

        List<String> result = jedis.hmget("user:104","name","age");
        for (String str : result) {
            System.out.println(str);
        }

        jedis.close();
    }

    @Test
    public void pojoTest() {

        Jedis jedis = jedisPool.getResource();

        User user =  new User(105,"tom","上海");
        Map<String,String> map = new HashMap<>();

        map.put("id", user.getId().toString());
        map.put("name",user.getName());
        map.put("address",user.getAddress());

        jedis.hmset("user:105",map);

        jedis.close();

    }

    @Test
    public void getPojo() {
        Jedis jedis = jedisPool.getResource();
        User user =  new User();

        Map<String,String> result = jedis.hgetAll("user:105");
        user.setId(Integer.valueOf(result.get("id")));
        user.setAddress(result.get("address"));
        user.setName(result.get("name"));

        System.out.println(user);

        jedis.close();
    }

}
