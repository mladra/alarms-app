package com.example.domain.alarm.control.handlers;

import com.example.api.AlarmEventDTO;
import com.example.domain.alarm.control.events.AlarmApplicationEvent;
import com.example.infrastructure.kafka.KafkaTopicsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlarmEventKafkaSender implements ApplicationListener<AlarmApplicationEvent> {

    private final static Logger logger = LoggerFactory.getLogger(AlarmEventKafkaSender.class);

    private final KafkaTemplate<String, AlarmEventDTO> kafkaTemplate;
    private final KafkaTopicsConfiguration topicsConfiguration;

    @Autowired
    public AlarmEventKafkaSender(KafkaTemplate<String, AlarmEventDTO> kafkaTemplate,
                                 KafkaTopicsConfiguration topicsConfiguration) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicsConfiguration = topicsConfiguration;
    }

    @Override
    public void onApplicationEvent(AlarmApplicationEvent event) {
        logger.info("Received application event: {}", event);
        AlarmEventDTO.AlarmStatusDTO status = AlarmEventDTO.AlarmStatusDTO.valueOf(event.getStatus().name());
        AlarmEventDTO kafkaEvent = new AlarmEventDTO(status, event.getSensorId(), event.getConditionName());
        kafkaTemplate.send(topicsConfiguration.getAlarmsTopicName(), String.valueOf(event.getSensorId()), kafkaEvent);
    }
}
