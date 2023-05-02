package com.example.kafka;

import com.example.api.SensorDataDTO;
import com.example.data.control.DataAnalyzer;
import com.example.data.entity.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataListener {

    private static final Logger log = LoggerFactory.getLogger(DataListener.class);
    private static final String TEMPERATURE = "temperature";
    private final DataAnalyzer dataAnalyzer;

    @Autowired
    public DataListener(DataAnalyzer dataAnalyzer) {
        this.dataAnalyzer = dataAnalyzer;
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
        dataAnalyzer.analyze(builder.build());
    }

}
