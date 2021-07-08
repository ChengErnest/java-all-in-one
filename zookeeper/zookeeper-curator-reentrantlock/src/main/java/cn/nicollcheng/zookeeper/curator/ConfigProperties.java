package cn.nicollcheng.zookeeper.curator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <b><code>ConfigProperties</code></b>
 * <p/>
 * Description Curator属性配置
 * <p/>
 * <b>Creation Time:</b> 2021/7/7 16:48.
 *
 * @author nicollcheng
 * @since zookeeper-curator 2021
 */
@Data
@Component
@ConfigurationProperties(prefix = "curator")
public class ConfigProperties {
    /**
     * 重试次数
     */
    private int retryCount;
    /**
     * 重试间隔时间
     */
    private int elapsedTimeMs;
    /**
     * 连接地址
     */
    private String connectString;
    /**
     * Session过期时间
     */
    private int sessionTimeoutMs;
    /**
     * 连接超时时间
     */
    private int connectionTimeoutMs;
    /**
     * 分布式锁根节点
     */
    private String rootLockNode = "/mutex_lock/";
    /**
     * 分布式锁锁超时时间，默认-1，获取锁，若失败则阻塞等待直到成功
     */
    private long lockTimeout = -1;

}