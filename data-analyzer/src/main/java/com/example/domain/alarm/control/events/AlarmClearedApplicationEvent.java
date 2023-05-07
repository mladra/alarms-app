package com.example.domain.alarm.control.events;

public class AlarmClearedApplicationEvent extends AlarmApplicationEvent {

    public AlarmClearedApplicationEvent(Object source, String name, Long sensorId) {
        super(source, name, sensorId, AlarmStatus.CLEAR);
    }
}
