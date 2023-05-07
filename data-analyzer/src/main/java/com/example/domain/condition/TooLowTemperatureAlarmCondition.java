package com.example.domain.condition;

import com.example.domain.alarm.entity.Sensor;
import org.springframework.stereotype.Component;

@Component
class TooLowTemperatureAlarmCondition implements AlarmCondition {

    private static final int CONDITIONS_OCCURRENCES_COUNT_TO_TRIGGER_ALARM = 3;
    private static final double MIN_TEMPERATURE = 18.0d;

    @Override
    public int occurrences() {
        return CONDITIONS_OCCURRENCES_COUNT_TO_TRIGGER_ALARM;
    }

    @Override
    public boolean isTriggered(Sensor sensor) {
        return sensor.getData().getTemperature() < MIN_TEMPERATURE;
    }
}
