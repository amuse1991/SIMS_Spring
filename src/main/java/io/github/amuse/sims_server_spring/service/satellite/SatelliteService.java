package io.github.amuse.sims_server_spring.service.satellite;

import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoDto;

import java.util.List;

public interface SatelliteService {
    List<SatelliteInfoDto> getSatelliteList(String satellieCode, int startAt, int maxResult);
    SatelliteInfoDto getSatelliteInfo(String satelliteCode);
    SatelliteInfoDto createSatellite(SatelliteInfoDto  reqForm);
    SatelliteInfoDto updateSatellite(String satelliteCode, SatelliteInfoDto reqForm);
    String deleteSatellite(String satelliteCode);
}
