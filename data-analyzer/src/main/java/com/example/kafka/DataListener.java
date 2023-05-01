package com.example.kafka;

import com.example.api.SensorDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataListener {

    private static final Logger log = LoggerFactory.getLogger(DataListener.class);
    private static final String URI = "http://alarm-notifier:8080/alarm-notifier/raise";

    private final RestTemplate restTemplate = new RestTemplate();

    @KafkaListener(topics = "sensors-data", containerFactory = "dataListenerContainerFactory")
    void handleMessage(SensorDataDTO data) {
        log.info("Received data: {}", data);
//        restTemplate.put(URI, data); TODO: mladra:
    }

}
