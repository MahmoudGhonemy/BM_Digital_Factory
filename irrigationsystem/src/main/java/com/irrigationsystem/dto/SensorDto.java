package com.irrigationsystem.dto;

import com.irrigationsystem.entity.Land;
import lombok.Data;

import java.time.LocalTime;

@Data
public class SensorDto {
    private long id;
    private Long timeToIrrigateAcreWith1lWater;

    private LocalTime lastIrrigationStartTime;
    private LocalTime lastIrrigationEndTime;
    private Land land;
}
