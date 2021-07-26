package cn.nicollcheng.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Jvm示例启动器
 * 添加启动参数
 *      -Xms256M -Xmx256M -XX:MetaspaceSize=50M -XX:MaxMetaspaceSize=50M
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/23 11:39.
 */
@SpringBootApplication
public class JvmBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(JvmBootstrap.class, args);
    }
}
