package com.example.sensors;

import java.util.Objects;

public class Sensor {

    private final Long id;
    private final String name;

    public Sensor(Long id) {
        Objects.requireNonNull(id, "Sensor id cannot be null");
        this.id = id;
        this.name = String.join("-", "sensor", String.valueOf(id));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
