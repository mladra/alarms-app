package com.example.domain.alarm.entity;

import java.util.Objects;
import java.util.Optional;

public class Alarm {

    private final String conditionName;
    private final Long sensorId;
    private final AlarmEntityStatus status;
    private final Long existingAlarmId;

    private Alarm(String conditionName, Long sensorId, AlarmEntityStatus status, Long existingAlarmId) {
        this.conditionName = conditionName;
        this.sensorId = sensorId;
        this.status = status;
        this.existingAlarmId = existingAlarmId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public AlarmEntityStatus getStatus() {
        return status;
    }

    public Optional<Long> getExistingAlarmId() {
        return Optional.ofNullable(existingAlarmId);
    }

    public enum AlarmEntityStatus {
        RAISE, CLEAR
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alarm that = (Alarm) o;
        return Objects.equals(conditionName, that.conditionName) && Objects.equals(sensorId, that.sensorId) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditionName, sensorId, status);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmEntity{");
        sb.append("conditionName='").append(conditionName).append('\'');
        sb.append(", sensorId=").append(sensorId);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {

        private String conditionName;
        private Long sensorId;
        private AlarmEntityStatus status = AlarmEntityStatus.RAISE;
        private Long existingAlarmId;

        private Builder() {
            // intentionally does nothing
        }

        public Builder withConditionName(String conditionName) {
            this.conditionName = conditionName;
            return this;
        }

        public Builder withSensorId(Long sensorId) {
            this.sensorId = sensorId;
            return this;
        }

        public Builder withStatus(AlarmEntityStatus status) {
            this.status = status;
            return this;
        }

        public Builder withExistingAlarmId(Long alarmId) {
            this.existingAlarmId = alarmId;
            return this;
        }

        public Alarm build() {
            Objects.requireNonNull(conditionName, "Condition name cannot be null");
            Objects.requireNonNull(sensorId, "Sensor identifier cannot be null");
            return new Alarm(this.conditionName, this.sensorId, this.status, this.existingAlarmId);
        }

    }
}
