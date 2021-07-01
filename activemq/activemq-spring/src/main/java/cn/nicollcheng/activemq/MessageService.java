package cn.nicollcheng.activemq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * <b><code>MessageService</code></b>
 * <p/>
 * Description 消息服务
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 17:23.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@Service
public class MessageService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "springQueue")
    private Destination springQueue;

    @Resource(name = "springTopic")
    private Destination springTopic;

    //向队列发送消息
    public void sendQueueMessage(String messageContent) {
        jmsTemplate.send(springQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage();
                // 设置消息内容
                msg.setText(messageContent);
                return msg;
            }
        });

    }

    //向主题发送消息
    public void sendTopicMessage(String messageContent) {
        jmsTemplate.send(springTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage();
                // 设置消息内容
                msg.setText(messageContent);
                return msg;
            }
        });

    }
}

