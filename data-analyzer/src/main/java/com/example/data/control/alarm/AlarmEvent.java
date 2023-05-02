package com.example.data.control.alarm;

import org.springframework.context.ApplicationEvent;

public abstract class AlarmEvent extends ApplicationEvent {

    private final String name;
    private final Long sensorId;

    AlarmEvent(Object source, String name, Long sensorId) {
        super(source);
        this.name = name;
        this.sensorId = sensorId;
    }

    public String getName() {
        return name;
    }

    public Long getSensorId() {
        return sensorId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmEvent{");
        sb.append("type='").append(getClass().getSimpleName()).append('\'');
        sb.append("sensorId='").append(sensorId).append('\'');
        sb.append("conditionName='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
