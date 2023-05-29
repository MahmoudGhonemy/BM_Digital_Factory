package com.irrigationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time-to-irrigate-acre-with-1lWater")
    private Long timeToIrrigateAcreWith1lWater;

    @Column(name = "last-irrigation-start-time")
    private LocalTime lastIrrigationStartTime;

    @Column(name = "last-irrigation-end-time")
    private LocalTime lastIrrigationEndTime;

    @OneToOne(fetch = FetchType.LAZY)
    private Land land;

}
