package com.example.data.control.alarm;

public class AlarmClearedEvent extends AlarmEvent {

    public AlarmClearedEvent(Object source, String name, Long sensorId) {
        super(source, name, sensorId);
    }
}
