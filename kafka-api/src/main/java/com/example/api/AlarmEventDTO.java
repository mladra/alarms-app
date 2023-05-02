package com.example.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AlarmEventDTO {

    private final AlarmType type;
    private final Long sensorId;
    private final String condition;

    @JsonCreator
    public AlarmEventDTO(@JsonProperty("type") AlarmType type, @JsonProperty("sensorId") Long sensorId, @JsonProperty("condition") String condition) {
        this.type = type;
        this.sensorId = sensorId;
        this.condition = condition;
    }

    public AlarmType getType() {
        return type;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public String getCondition() {
        return condition;
    }

    public enum AlarmType {
        RAISE, CLEAR
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlarmEventDTO that = (AlarmEventDTO) o;
        return type == that.type && Objects.equals(sensorId, that.sensorId) && Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sensorId, condition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmEventDTO{");
        sb.append("type=").append(type);
        sb.append(", sensorId=").append(sensorId);
        sb.append(", condition='").append(condition).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
