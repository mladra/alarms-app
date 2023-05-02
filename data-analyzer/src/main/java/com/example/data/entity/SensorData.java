package com.example.data.entity;

public class SensorData {

    private final double temperature;

    SensorData(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
