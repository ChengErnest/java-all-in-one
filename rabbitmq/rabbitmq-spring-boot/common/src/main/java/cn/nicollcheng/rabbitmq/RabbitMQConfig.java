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
    public static final String RABBITMQ_DIRECT_ROUTING = "direct_routing";
    /**
     * 队列主题名称
     */
    public static final String RABBITMQ_DIRECT_TOPIC = "direct_topic";
    /**
     * DIRECT 交换机名称
     */
    public static final String RABBITMQ_DIRECT_EXCHANGE = "direct_exchange";


    /**
     * FANOUT_EXCHANGE交换机类型的队列 A 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "fanout_A";

    /**
     * FANOUT_EXCHANGE交换机类型的队列 B 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "fanout_B";

    /**
     * FANOUT_EXCHANG交换机类型的名称
     */
    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";


    /**
     * TOPIC_EXCHANGE交换机名称
     */
    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange";

    /**
     * TOPIC_EXCHANGE交换机的队列A的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_A = "topic_queue_a";

    /**
     * TOPIC_EXCHANGE交换机的队列B的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_B = "topic_queue_b";

    /**
     * TOPIC_EXCHANGE交换机的队列C的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_C = "topic_queue_c";


    /**
     * HEADERS_EXCHANGE交换机名称
     */
    public static final String HEADERS_EXCHANGE_NAME = "headers_exchange";

    /**
     * HEADERS_EXCHANGE交换机的队列A的名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_A = "headers_queue_a";

    /**
     * HEADERS_EXCHANGE交换机的队列B的名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_B = "headers_queue_b";
}
