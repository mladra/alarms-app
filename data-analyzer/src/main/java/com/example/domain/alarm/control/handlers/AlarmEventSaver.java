package com.example.domain.alarm.control.handlers;

import com.example.domain.alarm.control.AlarmEventRepository;
import com.example.domain.alarm.control.events.AlarmApplicationEvent;
import com.example.domain.alarm.entity.Alarm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlarmEventSaver implements ApplicationListener<AlarmApplicationEvent> {

    private final static Logger logger = LoggerFactory.getLogger(AlarmEventSaver.class);

    private final AlarmEventRepository repository;

    @Autowired
    public AlarmEventSaver(AlarmEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(AlarmApplicationEvent event) {
        logger.info("Received application event: {}", event);
        if (event.getStatus().isCleared()) {
            handleClearEvent(event);
        } else {
            handleRaiseEvent(event);
        }
    }

    private void handleClearEvent(AlarmApplicationEvent event) {
        repository.findLastRaiseAlarmEvent(event.getSensorId(), event.getConditionName())
                .flatMap(Alarm::getExistingAlarmId)
                .map(ignoredId -> mapToAlarmEntity(event, Alarm.AlarmEntityStatus.CLEAR))
                .ifPresent(repository::save);
    }

    private void handleRaiseEvent(AlarmApplicationEvent event) {
        repository.findLastRaiseAlarmEvent(event.getSensorId(), event.getConditionName())
                .or(() -> Optional.of(mapToAlarmEntity(event, Alarm.AlarmEntityStatus.RAISE)))
                .ifPresent(repository::save);
    }

    private Alarm mapToAlarmEntity(AlarmApplicationEvent event, Alarm.AlarmEntityStatus status) {
        return Alarm.builder()
                .withConditionName(event.getConditionName())
                .withSensorId(event.getSensorId())
                .withStatus(status)
                .build();
    }
}
