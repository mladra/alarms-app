package com.example.domain.alarm.entity;

import java.util.Objects;

public class Sensor {

    private final Long id;
    private final String name;
    private final SensorData data;

    private Sensor(Long id, String name, SensorData data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SensorData getData() {
        return data;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sensor{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(id, sensor.id) && Objects.equals(name, sensor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static class Builder {

        private Long id;
        private String name;
        private SensorData sensorData;

        private Builder() {
            // intentionally empty
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withTemperature(double temperature) {
            this.sensorData = new SensorData(temperature);
            return this;
        }

        public Sensor build() {
            Objects.requireNonNull(id);
            Objects.requireNonNull(name);
            Objects.requireNonNull(sensorData);
            return new Sensor(id, name, sensorData);
        }
    }
}
