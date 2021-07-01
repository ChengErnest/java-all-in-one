package cn.nicollcheng.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * <b><code>Producer</code></b>
 * <p/>
 * Description 消息生产者
 * <p/>
 * <b>Creation Time:</b> 2021/6/29 11:50.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置RabbitMQ配置信息
        factory.setHost("192.168.0.59");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 创建连接
        Connection connection = factory.newConnection();
        // 获得信道
        Channel channel = connection.createChannel();
        // 声明交换机
        String exchange = "exchange-demo";
        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT, true);
        // 发送消息
        channel.basicPublish(exchange, "routeKey", null, "hello world!".getBytes(StandardCharsets.UTF_8));
        channel.close();
        connection.close();
    }
}
