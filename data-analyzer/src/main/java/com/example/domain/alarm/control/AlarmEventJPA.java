package com.example.domain.alarm.control;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "ALARMS_EVENTS")
class AlarmEventJPA {

    @Id
    @SequenceGenerator(name = "alarms-events-generator", sequenceName = "SEQ_ALARMS_EVENTS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alarms-events-generator")
    private Long eventId;

    @Column(name = "SENSOR_ID")
    private Long sensorId;

    @Column(name = "CONDITION")
    private String condition;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AlarmStatus type;

    @Column(name = "OCCURRENCE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date occurrenceTime;

    void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    void setCondition(String condition) {
        this.condition = condition;
    }

    void setStatus(AlarmStatus type) {
        this.type = type;
    }

    Long getEventId() {
        return eventId;
    }

    Long getSensorId() {
        return sensorId;
    }

    String getCondition() {
        return condition;
    }

    AlarmStatus getStatus() {
        return type;
    }

    Date getOccurrenceTime() {
        return occurrenceTime;
    }

    @PrePersist
    @PreUpdate
    void beforeInsert() {
        this.occurrenceTime = Date.from(Instant.now());
    }

    enum AlarmStatus {
        RAISE, CLEAR
    }
}
