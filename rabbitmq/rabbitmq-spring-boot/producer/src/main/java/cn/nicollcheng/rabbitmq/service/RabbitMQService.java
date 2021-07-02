package cn.nicollcheng.rabbitmq.service;

import java.util.Map;

/**
 * <b><code>RabbitMQService</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 18:01.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public interface RabbitMQService {
    /**
     *  发送消息 direct模式
     * @param msg 消息
     */
    String sendDirectMsg(String msg);
    /**
     *  发送消息 fanout模式
     * @param msg 消息
     */
    String sendFanoutMsg(String msg);
    /**
     *  发送消息 topic模式
     * @param msg 消息
     * @param routingKey 路由键
     */
    String sendTopicMsg(String msg, String routingKey);
    /**
     *  发送消息 headers模式
     * @param msg 消息
     * @param headers headers
     */
    String sendHeadersMsg(String msg, Map<String, Object> headers);
}
