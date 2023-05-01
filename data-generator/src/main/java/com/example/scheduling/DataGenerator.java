package com.example.scheduling;

import com.example.kafka.KafkaConfiguration;
import com.example.api.SensorDataDTO;
import com.example.sensors.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
class DataGenerator {

    private static final Logger log = LoggerFactory.getLogger(DataGenerator.class);

    private final KafkaTemplate<String, SensorDataDTO> kafkaTemplate;
    private final KafkaConfiguration kafkaConfiguration;
    private final Random random = new Random();

    @Value("${com.example.sensors.count:5}")
    private int sensorsCount;

    @Autowired
    DataGenerator(KafkaTemplate<String, SensorDataDTO> kafkaTemplate, KafkaConfiguration kafkaConfiguration) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfiguration = kafkaConfiguration;
    }

    @Scheduled(initialDelay = 10L, fixedDelay = 5L, timeUnit = TimeUnit.SECONDS)
    void sendMessage() {
        Sensor sensor = new Sensor(calculateSensorNumber());
        SensorDataDTO data = new SensorDataDTO(
                sensor.getId(),
                sensor.getName(),
                Map.of("temperature", String.format("%.2f", getTemperature()))
        );
        log.info("Sending data: {}", data);
        kafkaTemplate.send(kafkaConfiguration.getDataTopicName(), sensor.getName(), data);
    }

    private long calculateSensorNumber() {
        return Math.abs(random.nextLong()) % sensorsCount + 1;
    }

    private double getTemperature() {
        return random.nextInt(700) / 10d - 20d;
    }

}
