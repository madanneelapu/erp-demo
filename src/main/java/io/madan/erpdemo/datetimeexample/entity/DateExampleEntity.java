package io.madan.erpdemo.datetimeexample.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;


@Entity
@Table(name = "user_date_event")
@Data
public class DateExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_time", nullable = false)
    private Instant eventDateTime;

}
