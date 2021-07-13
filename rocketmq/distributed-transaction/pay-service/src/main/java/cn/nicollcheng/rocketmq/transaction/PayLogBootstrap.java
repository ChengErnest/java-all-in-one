package cn.nicollcheng.rocketmq.transaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/13 22:21.
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.nicollcheng.rocketmq.transaction.mapper")
public class PayLogBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(PayLogBootstrap.class, args);
    }
}
