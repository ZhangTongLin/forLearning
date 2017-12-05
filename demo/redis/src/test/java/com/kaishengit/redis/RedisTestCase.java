package com.kaishengit.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tonglin
 */
public class RedisTestCase {

    @Test
    public void setTest() {

        Jedis jedis = new Jedis("192.168.200.88",6379);

        jedis.set("name","jack");
        jedis.close();

    }

    @Test
    public void getTest() {
        Jedis jedis = new Jedis("192.168.200.88",6379);
        String str = jedis.get("name");
        System.out.println(str);
        jedis.close();
    }

    @Test
    public void setHash() {

        Jedis jedis = new Jedis("192.168.200.88",6379);
        Map<String, String> map = new HashMap<String, String>();
        map.put("name","jack");
        map.put("age","29");

        jedis.hmset("user:101",map);
        jedis.close();

    }

    @Test
    public void getHash() {
        Jedis jedis = new Jedis("192.168.200.88",6379);
//        List<String> result = jedis.hmget("user:101","name","age");
//        for (String str : result) {
//            System.out.println(str);
//        }

        Map<String, String> map = jedis.hgetAll("user:101");
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));

        jedis.close();
    }

    @Test
    public void setList() {
        Jedis jedis = new Jedis("192.168.200.88",6379);
        Long l = jedis.lpush("user:102:address","焦作","北京","上海");
        System.out.println(l);

        jedis.close();

    }

    @Test
    public void getList() {
        Jedis jedis = new Jedis("192.168.200.88",6379);
        List<String> result = jedis.lrange("user:102:address",0,-1);
        for (String str : result) {
            System.out.println(str);
        }
    }

    @Test
    public void poolTest() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        config.setMaxTotal(10);
        config.setMaxIdle(8);
        config.setMinIdle(2);

        JedisPool jedisPool = new JedisPool(config,"192.168.200.88");
        Jedis jedis = jedisPool.getResource();
        jedis.sadd("users","tom","jack","lucy");


        jedis.close();
        jedisPool.destroy();

    }

}
