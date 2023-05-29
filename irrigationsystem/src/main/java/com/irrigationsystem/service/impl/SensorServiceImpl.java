package com.irrigationsystem.service.impl;

import com.irrigationsystem.dto.SensorDto;
import com.irrigationsystem.entity.Crop;
import com.irrigationsystem.entity.Land;
import com.irrigationsystem.entity.Sensor;
import com.irrigationsystem.exception.ResourceNotFoundException;
import com.irrigationsystem.repository.CropRepository;
import com.irrigationsystem.repository.LandRepository;
import com.irrigationsystem.repository.SensorRepository;
import com.irrigationsystem.service.SensorServices;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class SensorServiceImpl implements SensorServices {
    private SensorRepository sensorRepository;
    private LandRepository landRepository;
    private CropRepository cropRepository;

    public SensorServiceImpl(SensorRepository sensorRepository, LandRepository landRepository, CropRepository cropRepository) {
        this.sensorRepository = sensorRepository;
        this.landRepository = landRepository;
        this.cropRepository = cropRepository;
    }

    /**
     * irrigate land with specific id
     *
     * @param id // land id
     * @return the end time of the irrigation
     */
    @Override
    public String irrigateLandById(long id) {
        String message ;
        Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sensor", "Land ID", id));
        if (LocalTime.now().compareTo(sensorRepository.findById(id).get().getLastIrrigationEndTime()) < 0) {
            message ="The sensor is already on \nThe irrigation is running to " +
                    sensorRepository.findById(id).get().getLastIrrigationEndTime();
        } else {
            Land land = landRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Land", "ID", id));
            Crop crop = cropRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Crop", "ID", id));
            sensor.setLastIrrigationStartTime(LocalTime.now());
            sensor.setLastIrrigationEndTime(LocalTime.now().plusMinutes(
                            crop.getWaterNeededByAcre() * sensor.getTimeToIrrigateAcreWith1lWater()
                    )
            );
            sensorRepository.save(sensor);

            message = "irrigation started at " + sensor.getLastIrrigationStartTime() + "\n" +
                    "And the irrigation will end at " + sensor.getLastIrrigationEndTime();

        }
        return message;
    }

}
