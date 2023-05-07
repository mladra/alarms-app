package com.example.domain.alarm.control;

import com.example.domain.alarm.entity.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AlarmEventRepository {

    private final AlarmEventJPARepository jpaRepository;

    @Autowired
    public AlarmEventRepository(AlarmEventJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Optional<Alarm> findLastRaiseAlarmEvent(Long sensorId, String conditionName) {
        return jpaRepository.findFirstBySensorIdAndConditionOrderByOccurrenceTimeDesc(sensorId, conditionName)
                .filter(alarm -> AlarmEventJPA.AlarmStatus.RAISE.equals(alarm.getStatus()))
                .map(this::mapToAlarmEntity);
    }

    public void save(Alarm alarm) {
        jpaRepository.save(mapToJPAEntity(alarm));
    }

    private Alarm mapToAlarmEntity(AlarmEventJPA alarmEventJPA) {
        return Alarm.builder()
                .withConditionName(alarmEventJPA.getCondition())
                .withSensorId(alarmEventJPA.getSensorId())
                .withStatus(Alarm.AlarmEntityStatus.valueOf(alarmEventJPA.getStatus().name()))
                .withExistingAlarmId(alarmEventJPA.getEventId())
                .build();
    }

    private AlarmEventJPA mapToJPAEntity(Alarm alarm) {
        AlarmEventJPA eventJPA = new AlarmEventJPA();
        eventJPA.setStatus(AlarmEventJPA.AlarmStatus.valueOf(alarm.getStatus().name()));
        eventJPA.setCondition(alarm.getConditionName());
        eventJPA.setSensorId(alarm.getSensorId());
        alarm.getExistingAlarmId().ifPresent(eventJPA::setEventId);
        return eventJPA;
    }
}
