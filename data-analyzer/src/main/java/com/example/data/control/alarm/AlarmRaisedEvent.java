package com.example.data.control.alarm;

public class AlarmRaisedEvent extends AlarmEvent {

    public AlarmRaisedEvent(Object source, String name, Long sensorId) {
        super(source, name, sensorId);
    }
}
