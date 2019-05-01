package io.github.amuse.sims_server_spring.service.satellite;

import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoReqDto;
import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoResDto;

import java.util.List;

public class SatelliteServiceImple implements SatelliteService {
    @Override
    public List<SatelliteInfoResDto> getSatelliteList(String satellieCode, int startAt, int maxResult) {
        return null;
    }

    @Override
    public SatelliteInfoResDto getSatelliteInfo(String satelliteCode) {
        return null;
    }

    @Override
    public SatelliteInfoResDto createSatellite(SatelliteInfoReqDto reqForm) {
        return null;
    }

    @Override
    public SatelliteInfoResDto updateSatellite(String satelliteCode, SatelliteInfoReqDto reqForm) {
        return null;
    }

    @Override
    public String deleteSatellite(String satelliteCode) {
        return null;
    }
}
