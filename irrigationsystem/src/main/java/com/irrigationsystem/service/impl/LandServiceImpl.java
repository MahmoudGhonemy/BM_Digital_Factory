package com.irrigationsystem.service.impl;

import com.irrigationsystem.dto.LandDto;
import com.irrigationsystem.entity.Crop;
import com.irrigationsystem.entity.Land;
import com.irrigationsystem.entity.Sensor;
import com.irrigationsystem.exception.ResourceNotFoundException;
import com.irrigationsystem.repository.CropRepository;
import com.irrigationsystem.repository.LandRepository;
import com.irrigationsystem.repository.SensorRepository;
import com.irrigationsystem.service.LandServices;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LandServiceImpl implements LandServices {
    private LandRepository landRepository;
    private CropRepository cropRepository;
    private SensorRepository sensorRepository;

    public LandServiceImpl(LandRepository landRepository, CropRepository cropRepository, SensorRepository sensorRepository) {
        this.landRepository = landRepository;
        this.cropRepository = cropRepository;
        this.sensorRepository = sensorRepository;
    }

    /**
     * Create land and add it into database
     *
     * @param landDto
     * @return created land
     */
    @Override
    public LandDto creatLand(LandDto landDto) {
        Land land = mapToEntity(landDto);
        Land newLand = landRepository.save(land);
        Sensor sensor = new Sensor();
        sensor.setId(newLand.getId());
        sensor.setLand(newLand);
        sensor.setLastIrrigationStartTime(LocalTime.now());
        sensor.setLastIrrigationEndTime(LocalTime.now());
        sensor.setTimeToIrrigateAcreWith1lWater(newLand.getTimeToIrrigateAcreWith1lWater());
        sensorRepository.save(sensor);
        return mapToDto(newLand);
    }

    @Override
    public void deleteLand(long id) {
        Land land = landRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Land", "id", id));
        landRepository.delete(land);
    }

    /**
     * Edit land area or sensor or the crop
     *
     * @param landDto
     * @param id      // land id
     * @return updated land
     */
    @Override
    public LandDto updateLand(LandDto landDto, long id) {
        Land land = landRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Land", "id", id));
        land.setArea(landDto.getArea());
        land.setTimeToIrrigateAcreWith1lWater(landDto.getTimeToIrrigateAcreWith1lWater());
        Crop crop = cropRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Crop", "id", id));
        crop.setName(landDto.getCrop().getName());
        crop.setWaterNeededByAcre(landDto.getCrop().getWaterNeededByAcre());
        crop.setId(id);
        land.setCrop(crop);
        landRepository.save(land);
        return mapToDto(land);
    }

    @Override
    public List<LandDto> getAllLands() {
        List<Land> lands = landRepository.findAll();
        return lands.stream().map(land -> mapToDto(land)).collect(Collectors.toList());
    }

    @Override
    public LandDto findById(long id) {
        Land land = landRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Land", "ID", id));
        return mapToDto(land);
    }

    /**
     * Mapping from dto to entity
     *
     * @param landDto
     * @return land entity
     */
    private Land mapToEntity(LandDto landDto) {
        Land land = new Land();
        land.setArea(landDto.getArea());
        land.setTimeToIrrigateAcreWith1lWater(landDto.getTimeToIrrigateAcreWith1lWater());

        Crop crop = new Crop();
        crop.setName(landDto.getCrop().getName());
        crop.setWaterNeededByAcre(landDto.getCrop().getWaterNeededByAcre());
        crop.setLand(land);

        land.setCrop(crop);

        return land;
    }

    /**
     * Mapping from entity to dto
     *
     * @param land
     * @return landDto
     */
    private LandDto mapToDto(Land land) {
        LandDto landDto = new LandDto();
        landDto.setId(land.getId());
        landDto.setArea(land.getArea());
        landDto.setTimeToIrrigateAcreWith1lWater(land.getTimeToIrrigateAcreWith1lWater());

        Crop crop = new Crop();
        crop.setId(land.getId());
        crop.setName(land.getCrop().getName());
        crop.setWaterNeededByAcre(land.getCrop().getWaterNeededByAcre());

//        crop.setLand(land); How to remove this Null ? I will answer you in the interview isA
        landDto.setCrop(crop);
        return landDto;
    }
}
