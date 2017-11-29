package com.kaishengit.springDemo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * @author Administrator.
 */
@Component
public class SpringConsumer{


    @JmsListener(destination = "spring-queue")
    public void dosomething(String message) {
        System.out.println(message);
    }
//    @Override
//    public void onMessage(Message message) {
//        TextMessage textMessage = (TextMessage) message;
//        try {
//            System.out.println(textMessage.getText());
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
}
