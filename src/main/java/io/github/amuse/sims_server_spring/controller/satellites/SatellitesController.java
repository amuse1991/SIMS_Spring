package io.github.amuse.sims_server_spring.controller.satellites;

import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoDto;
import io.github.amuse.sims_server_spring.dto.satellite.SatelliteMetaInfoDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;
import io.github.amuse.sims_server_spring.service.satellite.SatelliteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class SatellitesController {

    SatelliteService satelliteService;

    // 위성 정보
    @GetMapping("/satellites")
    public List<SatelliteInfoDto> getSatelliteList(@RequestParam(value = "code", required = false) String satellieCode,
                                                   @RequestParam(required = false, defaultValue = "0") int startAt,
                                                   @RequestParam(required = false, defaultValue = "50") int maxResult){
        return satelliteService.getSatelliteList(satellieCode,startAt,maxResult);
    }

    @GetMapping("/satellites/{satelliteCode}")
    public SatelliteInfoDto getSatelliteInfo(@PathVariable String satelliteCode){
        return satelliteService.getSatelliteInfo(satelliteCode);
    }

    @PostMapping("/satellites")
    public SatelliteInfoDto createSatellite(@RequestBody SatelliteInfoDto reqForm){
        return satelliteService.createSatellite(reqForm);
    }

    @PutMapping("/satellites/{satelliteCode}")
    public SatelliteInfoDto updateSatellite(@PathVariable String satelliteCode,
                                            @RequestBody SatelliteInfoDto reqForm){
        return satelliteService.updateSatellite(satelliteCode,reqForm);
    }

    @DeleteMapping("/satellites/{satelliteCode}")
    public String deleteSatellite(@PathVariable String satelliteCode){
        return satelliteService.deleteSatellite(satelliteCode);
    }

    @GetMapping("satellites/{satelliteCode}/meta")
    public SatelliteMetaInfoDto getTmMeta(@PathVariable String satelliteCode){
        return satelliteService.getSatelliteMeta(satelliteCode);
    }
}
