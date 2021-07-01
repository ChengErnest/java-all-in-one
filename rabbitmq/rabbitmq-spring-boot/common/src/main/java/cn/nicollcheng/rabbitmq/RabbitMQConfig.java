package cn.nicollcheng.rabbitmq;

/**
 * <b><code>RabbitMQConfig</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 17:56.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public class RabbitMQConfig {
    /**
     * DIRECT 交换机和队列绑定的匹配键 Direct Routing
     */
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "direct_routing";
    /**
     * 队列主题名称
     */
    public static final String RABBITMQ_DEMO_TOPIC = "direct_topic";
    /**
     * DIRECT 交换机名称
     */
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE = "direct_exchange";
}
