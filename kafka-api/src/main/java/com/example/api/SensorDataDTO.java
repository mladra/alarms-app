package com.example.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class SensorDataDTO {

    private final Long id;
    private final String name;
    private final Map<String, String> data;

    @JsonCreator
    public SensorDataDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name,
                         @JsonProperty("data") Map<String, String> data) {
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

    public Map<String, String> getData() {
        return Collections.unmodifiableMap(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDataDTO that = (SensorDataDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorDataDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
