package com.kaishengit.activmq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * @author Administrator.
 */
public class ActivemqTestCase {


    @Test
    public void sendMessage() throws JMSException {
        //1.创建工厂
        String brokerUrl = "tcp://localhost:61616";
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
        //2.创建connection连接并开启
        Connection connection = factory.createConnection();
        connection.start();
        //3.创建session会话 是否使用事务，定义签收模式
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地
        Destination destination = session.createQueue("text-message");
        //5创建消息生产者
        MessageProducer producer = session.createProducer(destination);
        //设置消息持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //6.发送消息

        for (int i = 0;i < 8;i ++){
            TextMessage textMessage = session.createTextMessage("hello,world" + i);
            producer.send(textMessage);
        }


        //session.commit();
        //7.释放资源
        producer.close();
        session.close();
        connection.close();
    }


    @Test
    public void consumer() throws JMSException, IOException {
        //1创建工厂
        String brokerUrl = "tcp://localhost:61616";
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
        //创建连接
        Connection connection = factory.createConnection();
        connection.start();
        //创建会话
        Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        //创建目的地
        Destination destination = session.createQueue("text-message");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //消费消息,监听队列中的消息，如果有新的消息，则会执行onMessage方法
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("textMessage>>>>>>" + textMessage.getText());
                    textMessage.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        //释放资源

        consumer.close();
        session.close();
        connection.close();
    }

    /**
     * 测试重试机制 rollBack
     * @throws JMSException
     */
    @Test
    public void rollBackTest() throws JMSException, IOException {
        String brokerUrl= "tcp://localhost:61616";
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);

        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setInitialRedeliveryDelay(2000);
        redeliveryPolicy.setRedeliveryDelay(2000);
        redeliveryPolicy.setMaximumRedeliveries(3);

        factory.setRedeliveryPolicy(redeliveryPolicy);


        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("text-message");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;

                try {
                    String text = textMessage.getText();
                    if (text.equals("hello,world6")) {
                        throw new JMSException("故意异常");
                    }
                    System.out.println(text);
                    session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                    try {
                        session.rollback();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }

}
