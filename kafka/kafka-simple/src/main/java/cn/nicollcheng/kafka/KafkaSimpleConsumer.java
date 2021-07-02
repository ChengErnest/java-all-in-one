package cn.nicollcheng.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * <b><code>KafkaSimpleConsumer</code></b>
 * <p/>
 * Description 消费者
 * <p/>
 * <b>Creation Time:</b> 2021/7/2 16:05.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public class KafkaSimpleConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.BOOTSTRAP_SERVERS);
        // 消费分组名
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaProperties.GROUP_ID);
        // 是否自动提交offset
        //props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 自动提交offset的间隔时间
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG , KafkaProperties.AUTO_COMMIT_INTERVAL_MS);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        /*
            心跳时间，服务端broker通过心跳确认consumer是否故障，如果发现故障，就会通过心跳下发
            rebalance的指令给其他的consumer通知他们进行rebalance操作，这个时间可以稍微短一点
        */
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);
        //服务端broker多久感知不到一个consumer心跳就认为他故障了，默认是10秒
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10 * 1000);
        /*
        如果两次poll操作间隔超过了这个时间，broker就会认为这个consumer处理能力太弱，
        会将其踢出消费组，将分区分配给别的consumer消费
        */
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 30 * 1000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 消费主题
        String topicName = KafkaProperties.SIMPLE_TOPIC;
        //consumer.subscribe(Arrays.asList(topicName));
        // 消费指定分区
        consumer.assign(Collections.singletonList(new TopicPartition(topicName, 0)));

        // 设置从那开始消费
        // 从头开始消费
//        consumer.seekToBeginning(Collections.singletonList(new TopicPartition(topicName, 0)));
        //指定offset消费
//        consumer.seek(new TopicPartition(topicName, 0), 10);

        while (true) {
            /*
             * poll() API 是拉取消息的长轮询，主要是判断consumer是否还活着，只要我们持续调用poll()，
             * 消费者就会存活在自己所在的group中，并且持续的消费指定partition的消息。
             * 底层是这么做的：消费者向server持续发送心跳，如果一个时间段（session.
             * timeout.ms）consumer挂掉或是不能发送心跳，这个消费者会被认为是挂掉了，
             * 这个Partition也会被重新分配给其他consumer
             */
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("收到消息：offset = %d, key = %s, value = %s%n", record.offset(), record.key(),
                        record.value());
            }
            if (records.count() > 0) {
                // 若不开启自动提交时，手动提交offset
                consumer.commitSync();
            }
        }
    }

}
