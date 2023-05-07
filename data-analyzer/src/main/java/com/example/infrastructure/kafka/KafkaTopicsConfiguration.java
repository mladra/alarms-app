package com.example.infrastructure.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicsConfiguration {

    @Value("${com.example.kafka.topics.alarms.name:alarms}")
    private String alarmsTopicName;

    @Value("${com.example.kafka.topics.alarms.partitions:1}")
    private int alarmsTopicPartitions;

    @Value("${com.example.kafka.topics.alarms.replicas:1}")
    private int alarmsTopicReplicas;

    public String getAlarmsTopicName() {
        return alarmsTopicName;
    }

    public int getAlarmsTopicPartitions() {
        return alarmsTopicPartitions;
    }

    public int getAlarmsTopicReplicas() {
        return alarmsTopicReplicas;
    }
}
