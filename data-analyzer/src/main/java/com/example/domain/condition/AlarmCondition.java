package com.example.domain.condition;

import com.example.domain.alarm.entity.Sensor;

public interface AlarmCondition {

    default String name() {
        return getClass().getSimpleName();
    }

    int occurrences();

    boolean isTriggered(Sensor sensor);

}
