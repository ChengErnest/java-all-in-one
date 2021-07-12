package cn.nicollcheng.seata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b><code>OrderApplication</code></b>
 * <p/>
 * Description 订单服务启动器
 * <p/>
 * <b>Creation Time:</b> 2021/7/12 13:40.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("cn.nicollcheng.seata.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
