package com.example.data.control;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ALARMS_EVENTS")
public class AlarmEventJPA {

    @Id
    @SequenceGenerator(name = "alarms-events-generator", sequenceName = "SEQ_ALARMS_EVENTS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alarms-events-generator")
    private Long eventId;

    @Column(name = "SENSOR_ID")
    private Long sensorId;

    @Column(name = "CONDITION")
    private String condition;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private AlarmEventType type;

    @Column(name = "OCCURRENCE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime occurrenceTime;

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setType(AlarmEventType type) {
        this.type = type;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public String getCondition() {
        return condition;
    }

    public AlarmEventType getType() {
        return type;
    }

    public OffsetDateTime getOccurrenceTime() {
        return occurrenceTime;
    }

    @PrePersist
    @PreUpdate
    void beforeInsert() {
        this.occurrenceTime = OffsetDateTime.now();
    }

    public enum AlarmEventType {
        RAISE, CLEARED
    }
}
