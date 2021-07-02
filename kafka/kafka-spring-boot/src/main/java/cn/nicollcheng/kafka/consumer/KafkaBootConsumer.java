package cn.nicollcheng.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaBootConsumer {

//    @KafkaListener(groupId = "testGroup", topicPartitions = {
//            @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
//            @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
//    },
//    //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
//    concurrency = "6")
    @KafkaListener(topics = "boot_topic",groupId = "boot_group")
    public void listen(ConsumerRecord<String, String> record) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }
}