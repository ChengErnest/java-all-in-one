package cn.nicollcheng.kafka;

/**
 * <b><code>KafkaProperties</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/2 15:59.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public interface KafkaProperties {
    String ZK_CONNECT = "127.0.0.1:2181";
    /**
     * kafka 集群连接的 host/port 组
     */
    String BOOTSTRAP_SERVERS = "localhost:9092";
    /**
     * Group Name
     */
    String GROUP_ID = "simple_group";
    /**
     * offset 是否自动提交
     */
    String ENABLE_AUTO_COMMIT = "true";
    /**
     * 自动提交 offset 到 zookeeper 的时间间隔，时间是毫秒
     */
    String AUTO_COMMIT_INTERVAL_MS = "1000";
    /**
     * 主题名称
     */
    String SIMPLE_TOPIC = "simple_topic";
}
