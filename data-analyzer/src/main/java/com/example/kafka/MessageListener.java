package com.example.kafka;

import com.example.api.SensorDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
    private static final String URI = "http://rest-service:8080/rest-service/message";

    private final RestTemplate restTemplate = new RestTemplate();

    @KafkaListener(topics = "messages", containerFactory = "messageListenerContainerFactory")
    void handleMessage(SensorDataDTO message) {
        log.info("Received message: {}", message);
        restTemplate.put(URI, message);
    }

}
