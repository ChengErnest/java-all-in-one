package cn.nicollcheng.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <b><code>CuratorConfiguration</code></b>
 * <p/>
 * Description Curator配置类
 * <p/>
 * <b>Creation Time:</b> 2021/7/7 16:45.
 *
 * @author nicollcheng
 * @since zookeeper-curator 2021
 */
@Configuration
public class CuratorConfiguration {

    private final ConfigProperties configProperties;

    @Autowired
    public CuratorConfiguration(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(
                configProperties.getElapsedTimeMs(),
                configProperties.getRetryCount());
        return CuratorFrameworkFactory.newClient(
                configProperties.getConnectString(),
                configProperties.getSessionTimeoutMs(),
                configProperties.getConnectionTimeoutMs(),
                retryPolicy);
    }
}
