package com.example.domain.alarm.control;

import com.example.domain.alarm.control.events.AlarmClearedApplicationEvent;
import com.example.domain.alarm.control.events.AlarmRaisedApplicationEvent;
import com.example.domain.condition.AlarmCondition;
import com.example.domain.alarm.entity.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SensorsDataAnalyzer {

    private final static Logger logger = LoggerFactory.getLogger(SensorsDataAnalyzer.class);

    private final Map<Long, Map<AlarmCondition, Long>> errorsCountBySensorIdAndCondition = new HashMap<>();
    private final Collection<AlarmCondition> conditions;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public SensorsDataAnalyzer(Collection<AlarmCondition> conditions, ApplicationEventPublisher eventPublisher) {
        this.conditions = conditions;
        this.eventPublisher = eventPublisher;
        logger.info("Discovered conditions: {}", this.conditions.stream().map(AlarmCondition::name).collect(Collectors.toSet()));
    }

    public Map<String, Long> getAlarmsCountByConditionName(Long sensorId) {
        Objects.requireNonNull(sensorId);
        if (!errorsCountBySensorIdAndCondition.containsKey(sensorId)) {
            return Collections.emptyMap();
        }

        return errorsCountBySensorIdAndCondition.get(sensorId).entrySet().stream()
                .map(entry -> Map.entry(entry.getKey().name(), entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Set<String> getAvailableConditionNames() {
        return conditions.stream()
                .map(AlarmCondition::name)
                .collect(Collectors.toSet());
    }

    public void analyze(Sensor sensor) {
        conditions.forEach(condition -> {
            if (condition.isTriggered(sensor)) {
                increaseError(sensor.getId(), condition);
            } else {
                clearError(sensor.getId(), condition);
            }
        });
        generateAlarms();
    }

    private void increaseError(Long sensorId, AlarmCondition condition) {
        if (!errorsCountBySensorIdAndCondition.containsKey(sensorId)) {
            errorsCountBySensorIdAndCondition.put(sensorId, new HashMap<>());
        }

        if (!errorsCountBySensorIdAndCondition.get(sensorId).containsKey(condition)) {
            errorsCountBySensorIdAndCondition.get(sensorId).put(condition, 1L);
        }

        long count = errorsCountBySensorIdAndCondition.get(sensorId).get(condition);
        errorsCountBySensorIdAndCondition.get(sensorId).put(condition, count + 1L);
    }

    private void clearError(Long sensorId, AlarmCondition condition) {
        if (!errorsCountBySensorIdAndCondition.containsKey(sensorId)) {
            logger.debug("No error are present for sensor with id: {}", sensorId);
            return;
        }

        if (!errorsCountBySensorIdAndCondition.get(sensorId).containsKey(condition)) {
            logger.debug("No error are present for sensor with id: {} and condition: {}", sensorId, condition);
            return;
        }

        errorsCountBySensorIdAndCondition.get(sensorId).remove(condition);
        eventPublisher.publishEvent(new AlarmClearedApplicationEvent(this, condition.name(), sensorId));
    }

    private void generateAlarms() {
        errorsCountBySensorIdAndCondition.forEach((sensorId, errorsCountByCondition) -> {
            errorsCountByCondition.forEach((condition, errorsCount) -> {
                if (errorsCount >= condition.occurrences()) {
                    eventPublisher.publishEvent(new AlarmRaisedApplicationEvent(this, condition.name(), sensorId));
                }
            });
        });
    }

}
