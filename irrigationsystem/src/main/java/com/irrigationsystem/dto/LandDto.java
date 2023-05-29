package com.irrigationsystem.dto;

import com.irrigationsystem.entity.Crop;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalTime;

@Data
public class LandDto {
    private Long id;
    private Long area;
    private Long timeToIrrigateAcreWith1lWater;
    private Crop crop;
}
