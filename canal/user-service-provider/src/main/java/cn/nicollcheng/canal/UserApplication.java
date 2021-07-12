package cn.nicollcheng.canal;

import cn.nicollcheng.canal.zookeeper.EmbeddedZooKeeper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PreDestroy;

@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.nicollcheng.canal.mapper")
public class UserApplication {
    private static EmbeddedZooKeeper embeddedZooKeeper = new EmbeddedZooKeeper(2181, false);
    public static void main(String[] args) {
        embeddedZooKeeper.start();
        SpringApplication.run(UserApplication.class, args);
    }
    @PreDestroy
    public void stop(){
        embeddedZooKeeper.stop();
    }
}
