package com.irrigationsystem.dto;

import com.irrigationsystem.entity.Land;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class CropDto {
    private Long id;
    private String name;
    private Long waterNeededByAcre;
    private Land land;
}
