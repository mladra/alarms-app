package com.example.domain.condition;

import com.example.domain.alarm.entity.Sensor;
import org.springframework.stereotype.Component;

@Component
class TooHighTemperatureAlarmCondition implements AlarmCondition {

    private static final int CONDITIONS_OCCURRENCES_COUNT_TO_TRIGGER_ALARM = 5;
    private static final double MAX_TEMPERATURE = 32.0d;

    @Override
    public int occurrences() {
        return CONDITIONS_OCCURRENCES_COUNT_TO_TRIGGER_ALARM;
    }

    @Override
    public boolean isTriggered(Sensor sensor) {
        return sensor.getData().getTemperature() >= MAX_TEMPERATURE;
    }
}
