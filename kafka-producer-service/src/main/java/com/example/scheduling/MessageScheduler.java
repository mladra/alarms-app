package com.example.scheduling;

import com.example.kafka.KafkaConfiguration;
import com.example.message.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class MessageScheduler {

    private static final Logger log = LoggerFactory.getLogger(MessageScheduler.class);
    private final KafkaTemplate<String, MessageDTO> kafkaTemplate;
    private final KafkaConfiguration kafkaConfiguration;

    @Autowired
    public MessageScheduler(KafkaTemplate<String, MessageDTO> kafkaTemplate, KafkaConfiguration kafkaConfiguration) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfiguration = kafkaConfiguration;
    }

    @Scheduled(initialDelay = 10L, fixedDelay = 5L, timeUnit = TimeUnit.SECONDS)
    void sendMessage() {
        MessageDTO message = new MessageDTO(
                UUID.randomUUID().toString(),
                "scheduler"
        );
        log.info("Sending message: {}", message);
        kafkaTemplate.send(kafkaConfiguration.getMessagesTopicName(), message);
    }

}
