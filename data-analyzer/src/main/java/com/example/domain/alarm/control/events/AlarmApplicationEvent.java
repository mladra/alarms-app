package com.example.domain.alarm.control.events;

import org.springframework.context.ApplicationEvent;

public abstract class AlarmApplicationEvent extends ApplicationEvent {

    private final String conditionName;
    private final Long sensorId;
    private final AlarmStatus status;

    protected AlarmApplicationEvent(Object source, String conditionName, Long sensorId, AlarmStatus status) {
        super(source);
        this.conditionName = conditionName;
        this.sensorId = sensorId;
        this.status = status;
    }

    public String getConditionName() {
        return conditionName;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public AlarmStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmEvent{");
        sb.append("type='").append(getClass().getSimpleName()).append('\'');
        sb.append("sensorId='").append(sensorId).append('\'');
        sb.append("conditionName='").append(conditionName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public enum AlarmStatus {
        RAISE, CLEAR;

        public boolean isCleared() {
            return this == CLEAR;
        }
    }
}
