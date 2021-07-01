package cn.nicollcheng.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * <b><code>MessageService</code></b>
 * <p/>
 * Description 消息监听器
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 17:23.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public class QueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                TextMessage txtMsg = (TextMessage) message;
                String messageStr = txtMsg.getText();
                System.out.println("队列监听器接收到文本消息：" + messageStr);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("只支持 TextMessage 类型消息！");
        }
    }
}
