package com.example.domain.alarm.boundary;

import com.example.domain.alarm.control.SensorsDataAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/alarms")
public class AlarmConditionResource {

    private final SensorsDataAnalyzer sensorsDataAnalyzer;

    @Autowired
    public AlarmConditionResource(SensorsDataAnalyzer sensorsDataAnalyzer) {
        this.sensorsDataAnalyzer = sensorsDataAnalyzer;
    }

    @GetMapping("/conditions")
    public ResponseEntity<Collection<String>> availableConditions() {
        return ResponseEntity.ok(sensorsDataAnalyzer.getAvailableConditionNames());
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<Map<String, Long>> alarmsByCondition(@PathVariable Long sensorId) {
        return ResponseEntity.ok(sensorsDataAnalyzer.getAlarmsCountByConditionName(sensorId));
    }
}
