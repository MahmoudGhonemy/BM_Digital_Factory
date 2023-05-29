package com.irrigationsystem.controller;

import com.irrigationsystem.service.SensorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/irrigate/")
public class SensorController {
    SensorServices sensorServices ;
    public SensorController(SensorServices sensorServices) {
        this.sensorServices = sensorServices;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> irrigateLandById (@PathVariable long id){
        return new ResponseEntity<>(sensorServices.irrigateLandById(id) , HttpStatus.OK) ;
    }
}
