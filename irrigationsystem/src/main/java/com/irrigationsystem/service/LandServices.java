package com.irrigationsystem.service;

import com.irrigationsystem.dto.LandDto;

import java.util.List;

public interface LandServices {
    LandDto creatLand(LandDto landDto);

    void deleteLand(long id);

    LandDto updateLand(LandDto landDto, long id);

    List<LandDto> getAllLands() ;

    LandDto findById(long id);
}
