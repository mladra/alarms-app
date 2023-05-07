package com.example.domain.alarm.control.listeners;

import com.example.domain.alarm.control.SensorsDataAnalyzer;
import com.example.api.SensorDataDTO;
import com.example.domain.alarm.entity.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorsDataListener {

    private static final Logger log = LoggerFactory.getLogger(SensorsDataListener.class);
    private static final String TEMPERATURE = "temperature";
    private final SensorsDataAnalyzer sensorsDataAnalyzer;

    @Autowired
    public SensorsDataListener(SensorsDataAnalyzer sensorsDataAnalyzer) {
        this.sensorsDataAnalyzer = sensorsDataAnalyzer;
    }

    @KafkaListener(topics = "sensors-data", containerFactory = "dataListenerContainerFactory")
    void handleMessage(SensorDataDTO data) {
        log.info("Received data: {}", data);
        Sensor.Builder builder = Sensor.builder()
                .withId(data.getId())
                .withName(data.getName());
        Optional.ofNullable(data.getData())
                .map(readings -> readings.get(TEMPERATURE))
                .map(Double::valueOf)
                .ifPresent(builder::withTemperature);
        sensorsDataAnalyzer.analyze(builder.build());
    }

}
