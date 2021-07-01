package cn.nicollcheng.rabbitmq.service;

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
     *  发送消息
     * @param msg 消息
     */
    String sendMsg(String msg);
}
