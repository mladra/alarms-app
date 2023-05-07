package com.example.domain.alarm.entity;

import java.util.Objects;

public class SensorData {

    private final double temperature;

    SensorData(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorData that = (SensorData) o;
        return Double.compare(that.temperature, temperature) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorData{");
        sb.append("temperature=").append(temperature);
        sb.append('}');
        return sb.toString();
    }
}
