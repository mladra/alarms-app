package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
class KafkaTopicsConfiguration {

    @Bean
    NewTopic kafkaDataTopic(KafkaConfiguration kafkaConfiguration) {
        return TopicBuilder.name(kafkaConfiguration.getDataTopicName())
                .partitions(kafkaConfiguration.getPartitionsCount())
                .replicas(kafkaConfiguration.getReplicasCount())
                .build();
    }

}
