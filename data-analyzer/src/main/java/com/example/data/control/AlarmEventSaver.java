package com.example.data.control;

import com.example.data.control.alarm.AlarmClearedEvent;
import com.example.data.control.alarm.AlarmEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlarmEventSaver implements ApplicationListener<AlarmEvent> {

    private final static Logger logger = LoggerFactory.getLogger(AlarmEventSaver.class);

    private final AlarmEventRepository repository;

    @Autowired
    public AlarmEventSaver(AlarmEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(AlarmEvent event) {
        logger.info("Received application event: {}", event);
        if (isAlarmCleared(event)) {
            repository.findFirstBySensorIdAndConditionOrderByOccurrenceTimeDesc(event.getSensorId(), event.getName())
                    .filter(alarm -> AlarmEventJPA.AlarmEventType.RAISE.equals(alarm.getType()))
                    .map(ignored -> mapToJPAEntity(event, AlarmEventJPA.AlarmEventType.CLEARED))
                    .ifPresent(repository::save);
        } else {
            repository.findFirstBySensorIdAndConditionOrderByOccurrenceTimeDesc(event.getSensorId(), event.getName())
                    .filter(alarm -> !AlarmEventJPA.AlarmEventType.CLEARED.equals(alarm.getType()))
                    .or(() -> Optional.of(mapToJPAEntity(event, AlarmEventJPA.AlarmEventType.RAISE)))
                    .ifPresent(repository::save);
        }
    }

    private boolean isAlarmCleared(AlarmEvent alarmEvent) {
        return alarmEvent instanceof AlarmClearedEvent;
    }

    private AlarmEventJPA mapToJPAEntity(AlarmEvent event, AlarmEventJPA.AlarmEventType type) {
        AlarmEventJPA entity = new AlarmEventJPA();
        entity.setType(type);
        entity.setCondition(event.getName());
        entity.setSensorId(event.getSensorId());
        return entity;
    }
}
