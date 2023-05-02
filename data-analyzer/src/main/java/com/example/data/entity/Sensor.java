package com.example.data.entity;

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
