package com.example.data.boundary;

import com.example.data.control.DataAnalyzer;
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

    private final DataAnalyzer dataAnalyzer;

    @Autowired
    public AlarmConditionResource(DataAnalyzer dataAnalyzer) {
        this.dataAnalyzer = dataAnalyzer;
    }

    @GetMapping("/conditions")
    public ResponseEntity<Collection<String>> availableConditions() {
        return ResponseEntity.ok(dataAnalyzer.getAvailableConditionNames());
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<Map<String, Long>> alarmsByCondition(@PathVariable Long sensorId) {
        return ResponseEntity.ok(dataAnalyzer.getAlarmsCountByConditionName(sensorId));
    }
}
