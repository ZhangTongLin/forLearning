package com.kaishengit.redis;

import com.alibaba.fastjson.JSON;
import com.kaishengit.pojo.User;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Tonglin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringPojoRedisTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void setTest() {
        User user = new User(106,"李四","北京");

        Jedis jedis = jedisPool.getResource();
        //将对象序列化为字节数组

        Schema<User> schema = RuntimeSchema.getSchema(User.class);
        byte[] bytes = ProtostuffIOUtil.toByteArray(user,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        jedis.set("user:107".getBytes(),bytes);

        jedis.close();

    }

    @Test
    public void getProtostuff() {
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get("user:107".getBytes());

        Schema<User> schema = RuntimeSchema.getSchema(User.class);

        User user = new User();

        //将对象反序列化,并封装
        ProtostuffIOUtil.mergeFrom(bytes,user,schema);

        System.out.println(user);

        jedis.close();

    }

    @Test
    public void setUser() {

        User user = new User(108,"王五","南京");
        Jedis jedis = jedisPool.getResource();

        //转化为json进行存储
        jedis.set("user:108", JSON.toJSONString(user));

        jedis.close();

    }

    @Test
    public void getUser() {
        User user = new User();

        Jedis jedis = jedisPool.getResource();

        String result = jedis.get("user:108");

        user = JSON.parseObject(result,User.class);

        System.out.println(user);

        System.out.println(result);

        jedis.close();
    }

}
