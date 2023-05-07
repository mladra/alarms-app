package com.example.domain.alarm.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface AlarmEventJPARepository extends JpaRepository<AlarmEventJPA, Long> {

    Optional<AlarmEventJPA> findFirstBySensorIdAndConditionOrderByOccurrenceTimeDesc(Long sensorId, String condition);

}
