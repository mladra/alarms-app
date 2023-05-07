package com.example.domain.alarm.control.events;

public class AlarmRaisedApplicationEvent extends AlarmApplicationEvent {

    public AlarmRaisedApplicationEvent(Object source, String name, Long sensorId) {
        super(source, name, sensorId, AlarmStatus.RAISE);
    }
}
