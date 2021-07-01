package cn.nicollcheng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * <b><code>Consumer</code></b>
 * <p/>
 * Description 消费者
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 17:08.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public class Consumer {
    public static void main(String[] args) throws JMSException, InterruptedException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                ActiveMQConnectionFactory.DEFAULT_BROKER_URL
        );
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("activemq-simple-topic");
        MessageConsumer consumer1 = session.createConsumer(topic);
        consumer1.setMessageListener(message -> {
            try {
                System.out.println("消费者1 接收到消息：" + ((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        MessageConsumer consumer2 = session.createConsumer(topic);
        consumer2.setMessageListener(message -> {
            try {
                System.out.println("消费者2 接收到消息：" + ((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(10 * 1000);
        //关闭资源
        session.close();
        connection.close();
    }
}
