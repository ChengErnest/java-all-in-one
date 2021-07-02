package cn.nicollcheng.rabbitmq.config;

import cn.nicollcheng.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <b><code>DirectRabbitConfig</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/1 17:54.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
//实现BeanPostProcessor类，使用Bean的生命周期函数
@Component
public class HeadersRabbitConfig implements BeanPostProcessor {
    //这是创建交换机和队列用的rabbitAdmin对象
    @Resource
    private RabbitAdmin rabbitAdmin;

    //实例化bean后，也就是Bean的后置处理器
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        rabbitAdmin.declareExchange(rabbitmqHeadersExchange());
        rabbitAdmin.declareQueue(headersQueueA());
        rabbitAdmin.declareQueue(headersQueueB());
        return null;
    }
    @Bean
    public Queue headersQueueA() {
        return new Queue(RabbitMQConfig.HEADERS_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue headersQueueB() {
        return new Queue(RabbitMQConfig.HEADERS_EXCHANGE_QUEUE_B, true, false, false);
    }

    @Bean
    public HeadersExchange rabbitmqHeadersExchange() {
        return new HeadersExchange(RabbitMQConfig.HEADERS_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding bindHeadersA() {
        //{"key_one":"java","key_two":"rabbit"}
        Map<String, Object> map = new HashMap<>();
        map.put("key_one", "java");
        map.put("key_two", "rabbit");
        //全匹配
        return BindingBuilder.bind(headersQueueA())
                .to(rabbitmqHeadersExchange())
                .whereAll(map).match();
    }

    @Bean
    public Binding bindHeadersB() {
        //{"headers_A":"coke"} or {"headers_B":"sky"}
        Map<String, Object> map = new HashMap<>();
        map.put("headers_A", "coke");
        map.put("headers_B", "sky");
        //部分匹配
        return BindingBuilder.bind(headersQueueB())
                .to(rabbitmqHeadersExchange())
                .whereAny(map).match();
    }
}
