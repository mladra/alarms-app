package com.example.data.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlarmEventRepository extends JpaRepository<AlarmEventJPA, Long> {

    Optional<AlarmEventJPA> findAlarmEventJPABySensorIdAndConditionOrderByOccurrenceTimeDesc(Long sensorId, String condition);

}
