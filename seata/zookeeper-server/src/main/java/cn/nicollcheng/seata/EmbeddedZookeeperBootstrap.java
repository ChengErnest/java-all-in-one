package cn.nicollcheng.seata;

import io.github.raonigabriel.embedded.zookeeper.annotation.EnableEmbeddedZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b><code>EmbeddedStandaloneZookeeperServer</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/12 14:22.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@EnableEmbeddedZooKeeper
@SpringBootApplication
public class EmbeddedZookeeperBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedZookeeperBootstrap.class, args);
    }

}
