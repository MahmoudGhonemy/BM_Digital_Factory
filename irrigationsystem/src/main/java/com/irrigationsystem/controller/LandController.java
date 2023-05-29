package com.irrigationsystem.controller;

import com.irrigationsystem.dto.LandDto;
import com.irrigationsystem.service.LandServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/lands")
public class LandController {
    LandServices landServices;

    public LandController(LandServices landServices) {
        this.landServices = landServices;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<LandDto> createLand(@RequestBody LandDto landDto) {
        return new ResponseEntity<>(landServices.creatLand(landDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLand(@PathVariable(name = "id") long id) {
        landServices.deleteLand(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandDto> updateLand(@RequestBody LandDto landDto, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(landServices.updateLand(landDto, id), HttpStatus.OK);
    }

    @GetMapping
    public List<LandDto> getAllLands (){
        return landServices.getAllLands();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LandDto> findById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(landServices.findById(id), HttpStatus.OK);
        //return ResponseEntity.ok(postService.findById(id));
    }

}
