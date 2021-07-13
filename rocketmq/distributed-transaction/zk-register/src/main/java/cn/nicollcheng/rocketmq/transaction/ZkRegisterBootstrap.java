package cn.nicollcheng.rocketmq.transaction;

import io.github.raonigabriel.embedded.zookeeper.annotation.EnableEmbeddedZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/13 22:44.
 */
@EnableEmbeddedZooKeeper
@SpringBootApplication
public class ZkRegisterBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(ZkRegisterBootstrap.class, args);
    }
}
