package com.example.data.control;

import com.example.api.AlarmEventDTO;
import com.example.data.control.alarm.AlarmClearedEvent;
import com.example.data.control.alarm.AlarmEvent;
import com.example.kafka.KafkaTopicsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlarmEventKafkaSender implements ApplicationListener<AlarmEvent> {

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
    public void onApplicationEvent(AlarmEvent event) {
        logger.info("Received application event: {}", event);
        AlarmEventDTO.AlarmType type = computeAlarmType(event);
        AlarmEventDTO kafkaEvent = new AlarmEventDTO(type, event.getSensorId(), event.getName());
        kafkaTemplate.send(topicsConfiguration.getAlarmsTopicName(), String.valueOf(event.getSensorId()), kafkaEvent);
    }

    private AlarmEventDTO.AlarmType computeAlarmType(AlarmEvent alarmEvent) {
        if (alarmEvent instanceof AlarmClearedEvent) {
            return AlarmEventDTO.AlarmType.CLEAR;
        }

        return AlarmEventDTO.AlarmType.RAISE;
    }
}
