package com.example.data.control;

import com.example.data.control.alarm.AlarmClearedEvent;
import com.example.data.control.alarm.AlarmEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
            AlarmEventJPA entity = new AlarmEventJPA();
            entity.setType(AlarmEventJPA.AlarmEventType.CLEARED);
            entity.setCondition(entity.getCondition());
            entity.setSensorId(entity.getSensorId());
            repository.save(entity);
        } else {
            repository.findAlarmEventJPABySensorIdAndConditionOrderByOccurrenceTimeDesc(event.getSensorId(), event.getName())
                    .filter(alarm -> !AlarmEventJPA.AlarmEventType.CLEARED.equals(alarm.getType()))
                    .ifPresent(repository::save);
        }
    }

    private boolean isAlarmCleared(AlarmEvent alarmEvent) {
        return alarmEvent instanceof AlarmClearedEvent;
    }
}
