package cn.nicollcheng.rabbitmq.listener.fanout;

import cn.nicollcheng.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <b><code>RabbitListener</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 18:07.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@Component
//使用queuesToDeclare属性，如果不存在则会创建队列
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_B))
public class RabbitmqFanoutBListener {

    @RabbitHandler
    public void process(Map map){
        System.out.println("收到消息：" + map.toString());
    }
}
