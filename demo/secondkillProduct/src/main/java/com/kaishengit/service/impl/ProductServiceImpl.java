package com.kaishengit.service.impl;

import com.alibaba.fastjson.JSON;
import com.kaishengit.entity.Product;
import com.kaishengit.entity.ProductExample;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.service.ProductService;
import com.kaishengit.service.exception.ServiceException;
import com.kaishengit.util.KillProductJob;
import com.kaishengit.util.QiniuUtil;
import org.joda.time.DateTime;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Tonglin
 */
@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    /**
     * 保存一个新的促销商品
     *
     * @param product
     * @param stime
     * @param eTime
     * @param imageInputStream
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addProduct(Product product, String stime, String eTime, InputStream imageInputStream) {


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = dateFormat.parse(stime);
            endTime = dateFormat.parse(eTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        product.setStartTime(startTime);
        product.setEndTime(endTime);

        if (imageInputStream != null) {
            String name = QiniuUtil.qiniuUpload(imageInputStream);
            product.setProductImage(name);
        }
        productMapper.insertSelective(product);

        try(Jedis jedis = jedisPool.getResource();) {
            for (int i = 0; i < product.getProductInventory(); i++) {
                jedis.lpush("product:inventory" + product.getId(), String.valueOf(i));
                logger.info("放进list里面一个值");
            }
        }

        //添加一个定时任务，在秒杀结束后 更新数据库信息;

        addSchedulerEndTime(product.getEndTime().getTime(),product.getId());
    }

    private void addSchedulerEndTime(Long endTime, Integer id) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAsString("id",id);

        JobDetail jobDetail = JobBuilder
                                .newJob(KillProductJob.class)
                                .setJobData(jobDataMap)
                                .withIdentity(new JobKey("productId:" + id,"productInventoryGroup"))
                                .build();

        DateTime dateTime = new DateTime(endTime);

        StringBuilder cron = new StringBuilder("0")
                .append(" ")
                .append(dateTime.getMinuteOfHour())
                .append(" ")
                .append(dateTime.getHourOfDay())
                .append(" ")
                .append(dateTime.getDayOfMonth())
                .append(" ")
                .append(dateTime.getMonthOfYear())
                .append(" ? ")
                .append(dateTime.getYear());

        logger.info("CRON EX: {}" ,cron.toString());

        ScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule(cron.toString());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(scheduleBuilder)
                .build();

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception ex) {
            throw new ServiceException(ex,"添加定时任务异常");
        }
    }

    /**
     * 查询所有秒杀商品
     *
     * @return
     */
    @Override
    public List<Product> findAllProduct() {
        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("start_time desc");

        return productMapper.selectByExampleWithBLOBs(productExample);
    }

    /**
     * 根据id查询商品详情
     *
     * @param id
     * @return
     */
    @Override
    public Product findProductById(Integer id) {

        try(Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists("product:" + id)) {
                Product product = JSON.parseObject(jedis.get("product:" + id), Product.class);
                return product;
            } else {
                Product product = productMapper.selectByPrimaryKey(id);
                if (product != null) {
                    jedis.set("product:" + id, JSON.toJSONString(product));
                    return product;
                } else {
                    throw new ServiceException("商品不存在");
                }
            }
        }
    }

    /**
     * 根据id进行商品的秒杀
     *
     * @param id
     */
    @Override
    public void killProductById(Integer id) throws RuntimeException {

        try(Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists("product:" + id)) {
                Product product = JSON.parseObject(jedis.get("product:" + id), Product.class);

                if (!product.isStart()) {
                    throw new ServiceException("你来早了");
                }
                if (product.isEnd()) {
                    throw new ServiceException("你来晚了");
                }

                String value = jedis.lpop("product:inventory" + id);

                if (value == null) {
                    logger.info("抢购商品{}失败",id);
                    throw new ServiceException("抢光了");
                } else {
                    product.setProductInventory(product.getProductInventory() - 1);
                    jedis.set("product:" + id,JSON.toJSONString(product));
                    logger.info("抢购商品{}成功",id);
//                    //添加到队列
//                    sendMessage(id);

                }

            } else {
                throw new ServiceException("该商品不存在");
            }
        }

        /*Product product = productMapper.selectByPrimaryKey(id);
        if (product != null) {
            if (product.getProductInventory() > 0) {

                product.setProductInventory(product.getProductInventory() - 1);
                Jedis jedis = jedisPool.getResource();
                jedis.set("product:" + id, JSON.toJSONString(product));

                productMapper.updateByPrimaryKeyWithBLOBs(product);
                logger.info("抢购商品{}成功",id);
            } else {
                logger.info("抢购商品{}失败",id);
                throw new ServiceException("抢光了");
            }

        } else {
            throw new ServiceException("该商品不存在");
        }*/
    }

    /*private void sendMessage(Integer id) {
        jmsTemplate.send("kill-product-queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(id.toString());
                return textMessage;
            }
        });
    }*/
}
