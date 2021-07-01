package cn.nicollcheng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import java.util.Objects;

/**
 * <b><code>Producer</code></b>
 * <p/>
 * Description 消息生产者
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 16:56.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public class Producer {
    public static void main(String[] args) throws JMSException {
        // 创建工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                ActiveMQConnectionFactory.DEFAULT_BROKER_URL
        );
        Connection connection = null;
        Session session = null;
        try {
            // 创建会话
            connection = factory.createConnection();
            // 开启连接
            connection.start();
            // 创建会话，不需要事务
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建 topic
            Topic topic = session.createTopic("activemq-simple-topic");
            // 发送消息
            MessageProducer producer = session.createProducer(topic);
            for (int i = 0; i < 10; i++) {
                TextMessage message = session.createTextMessage("发送消息: " + i);
                producer.send(topic, message);
            }
        } finally {
            // 关闭资源
            if (!Objects.isNull(session)) {
                session.close();
            }
            if (!Objects.isNull(connection)) {
                connection.close();
            }
        }

    }
}
