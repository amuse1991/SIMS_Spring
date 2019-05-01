package io.github.amuse.sims_server_spring.service.satellite;

import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoReqDto;
import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoResDto;

import java.util.List;

public interface SatelliteService {
    List<SatelliteInfoResDto> getSatelliteList(String satellieCode, int startAt, int maxResult);
    SatelliteInfoResDto getSatelliteInfo(String satelliteCode);
    SatelliteInfoResDto createSatellite(SatelliteInfoReqDto reqForm);
    SatelliteInfoResDto updateSatellite(String satelliteCode, SatelliteInfoReqDto reqForm);
    String deleteSatellite(String satelliteCode);
}
