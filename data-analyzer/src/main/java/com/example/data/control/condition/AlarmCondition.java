package com.example.data.control.condition;

import com.example.data.entity.Sensor;

public interface AlarmCondition {

    default String name() {
        return getClass().getSimpleName();
    }

    int occurrences();

    boolean isTriggered(Sensor sensor);

}
