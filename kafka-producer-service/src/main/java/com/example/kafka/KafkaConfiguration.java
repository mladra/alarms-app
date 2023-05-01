package com.example.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Value("${com.example.kafka.topics.messages.name:messages}")
    private String messagesTopicName;

    @Value("${com.example.kafka.topics.messages.partitions:1}")
    private int partitionsCount;

    @Value("${com.example.kafka.topics.messages.replicas:1}")
    private int replicasCount;

    public String getMessagesTopicName() {
        return messagesTopicName;
    }

    public int getPartitionsCount() {
        return partitionsCount;
    }

    public int getReplicasCount() {
        return replicasCount;
    }
}
