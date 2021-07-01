package cn.nicollcheng.activemq;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * <b><code>Application</code></b>
 * <p/>
 * Description 启动器
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 17:29.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        MessageService bean = (MessageService) context.getBean("messageService");
        for (int i = 0; i < 5; i++) {
            bean.sendQueueMessage("队列消息鸭" + i);
        }
        for (int i = 0; i < 5; i++) {
            bean.sendTopicMessage("主题消息鸭" + i);
        }
    }
}
