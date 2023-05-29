package com.irrigationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "land")
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "area", nullable = false)
    private Long area;
    @Column(name = "time-to-irrigate-acre-with-1lWater", nullable = false)
    private Long timeToIrrigateAcreWith1lWater;

    @OneToOne(mappedBy = "land", cascade = CascadeType.ALL, orphanRemoval = true)
    private Crop crop;

    @OneToOne(mappedBy = "land", cascade = CascadeType.ALL, orphanRemoval = true)
    private Sensor sensor ;


}
