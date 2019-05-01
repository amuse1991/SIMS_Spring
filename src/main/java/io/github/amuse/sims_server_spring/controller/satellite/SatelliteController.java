package io.github.amuse.sims_server_spring.controller.satellite;

import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoReqDto;
import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoResDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SatelliteController {

    // 위성 정보
    @GetMapping("/satellites")
    public List<SatelliteInfoResDto> getSatelliteList(@RequestParam(value = "code", required = false) String satellieCode,
                                                      @RequestParam(required = false, defaultValue = "0") int startAt,
                                                      @RequestParam(required = false, defaultValue = "50") int maxResult){
        return null;
    }

    @GetMapping("/satellites/{satelliteCode}")
    public SatelliteInfoResDto getSatelliteInfo(@PathVariable String satelliteCode){
        return null;
    }

    @PostMapping("/satellites")
    public SatelliteInfoResDto createSatellite(@RequestBody SatelliteInfoReqDto reqForm){
        return null;
    }

    @PutMapping("/satellites/{satelliteCode}")
    public SatelliteInfoResDto updateSatellite(@PathVariable String satelliteCode,
                                               @RequestBody SatelliteInfoReqDto reqForm){
        return null;
    }

    @DeleteMapping("/satellites/{satelliteCode}")
    public String deleteSatellite(@PathVariable String satelliteCode){
        return null;
    }

    // 위성의 TM 정보

    // 위성의 TC 정보
}
