package com.example.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Value("${com.example.kafka.topics.data.name:sensors-data}")
    private String dataTopicName;

    @Value("${com.example.kafka.topics.data.partitions:1}")
    private int partitionsCount;

    @Value("${com.example.kafka.topics.data.replicas:1}")
    private int replicasCount;

    public String getDataTopicName() {
        return dataTopicName;
    }

    public int getPartitionsCount() {
        return partitionsCount;
    }

    public int getReplicasCount() {
        return replicasCount;
    }
}
