package io.github.amuse.sims_server_spring.controller.satellites;

import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoDto;
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

    // 위성의 TM 메타 정보
    @GetMapping("satellites/{satelliteCode}/telemetry")
    public List<TmMetaResDto> getTmMeta(@PathVariable String satelliteCode){
        return null;
    }

    @PostMapping("satellites/{satelliteCode}/telemetry")
    public TmMetaResDto insertTmMeta(@PathVariable String satelliteCode, @RequestBody TmMetaReqDto reqForm){
        return null;
    }

    @PutMapping("satellites/{satelliteCode}/telemetry")
    public TmMetaResDto updateTmMeta(@PathVariable String satelliteCode, @RequestBody TmMetaReqDto reqForm){
        return null;
    }

    @DeleteMapping("satellites/{satelliteCode}/telemetry")
    public TmMetaResDto deleteTmMeta(@PathVariable String satelliteCode, @RequestParam(value = "code") String tmCode){
        return null;
    }

    // 위성의 TC 메타 정보
    @GetMapping("satellites/{satelliteCode}/telecommand")
    public List<TcMetaResDto> getTcMeta(@PathVariable String satelliteCode){
        return null;
    }

    @PostMapping("satellites/{satelliteCode}/telecommand")
    public TcMetaResDto insertTcMeta(@PathVariable String satelliteCode, @RequestBody TcMetaReqDto reqForm){
        return null;
    }

    @PutMapping("satellites/{satelliteCode}/telecommand")
    public TcMetaResDto updateTcMeta(@PathVariable String satelliteCode, @RequestBody TcMetaReqDto reqForm){
        return null;
    }

    @DeleteMapping("satellites/{satelliteCode}/telecommand")
    public TcMetaResDto deleteTcMeta(@PathVariable String satelliteCode, @RequestParam(value = "code") String tmCode){
        return null;
    }
}
