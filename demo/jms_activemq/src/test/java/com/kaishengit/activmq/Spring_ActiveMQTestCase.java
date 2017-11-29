package com.kaishengit.activmq;

import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * @author Administrator.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Spring_ActiveMQTestCase {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQTopic topic;


    @Test
    public void sendMessageQueue() throws IOException {

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("hello,springJMS");
                return textMessage;
            }
        });
        //System.in.read();

    }

    @Test
    public void springTopicTest() throws IOException {

        jmsTemplate.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("hello,springTopic");
                return textMessage;
            }
        });
        //System.in.read();

    }




}
