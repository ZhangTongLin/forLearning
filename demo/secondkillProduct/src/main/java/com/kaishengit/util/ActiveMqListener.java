package com.kaishengit.util;

import com.kaishengit.entity.Product;
import com.kaishengit.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Tonglin
 */
@Component
public class ActiveMqListener {

    @Autowired
    private ProductMapper productMapper;

    @JmsListener(destination = "kill-product-queue",containerFactory = "jmsListenerContainerFactory")
    public void doSomething(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            Integer id = Integer.valueOf(textMessage.getText());

            Product product = productMapper.selectByPrimaryKey(id);
            product.setProductInventory(product.getProductInventory() - 1);

            productMapper.updateByPrimaryKeyWithBLOBs(product);

            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


 /*   @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            Integer id = Integer.valueOf(textMessage.getText());
            Product product = productMapper.selectByPrimaryKey(id);
            product.setProductInventory(product.getProductInventory() -1);
            productMapper.updateByPrimaryKeyWithBLOBs(product);
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }*/
}
