package cn.nicollcheng.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * <b><code>Consumer</code></b>
 * <p/>
 * Description 消息消费者
 * <p/>
 * <b>Creation Time:</b> 2021/6/29 14:05.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
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
        // 声明队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定队列，通过 routeKey 将队列和交换机绑定起来
        channel.queueBind(queue, "exchange-demo", "routeKey");
        while (true){
            // 消费消息
            String consumerTag = "";
            channel.basicConsume(queue, false, consumerTag, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("路由键：" + routingKey + "，内容类型：" + contentType);
                    // 确认消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    System.out.println("接收到：" + new String(body, StandardCharsets.UTF_8));
                }
            });
        }
    }
}
