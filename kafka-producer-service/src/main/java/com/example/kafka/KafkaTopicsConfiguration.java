package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfiguration {

    @Bean
    NewTopic kafkaMessagesTopic(KafkaConfiguration kafkaConfiguration) {
        return TopicBuilder.name(kafkaConfiguration.getMessagesTopicName())
                .partitions(kafkaConfiguration.getPartitionsCount())
                .replicas(kafkaConfiguration.getReplicasCount())
                .build();
    }

}
