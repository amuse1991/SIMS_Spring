package io.github.amuse.sims_server_spring.service.satellite;

import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoDto;
import io.github.amuse.sims_server_spring.dto.satellite.SatelliteMetaInfoDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;

import java.util.List;

public interface SatelliteService {
    // 위성 정보
    List<SatelliteInfoDto> getSatelliteList(String satellieCode, int startAt, int maxResult);
    SatelliteInfoDto getSatelliteInfo(String satelliteCode);
    SatelliteInfoDto createSatellite(SatelliteInfoDto reqForm);
    SatelliteInfoDto updateSatellite(String satelliteCode, SatelliteInfoDto reqForm);
    String deleteSatellite(String satelliteCode);

    // 위성의 메타 정보
    SatelliteMetaInfoDto getSatelliteMeta(String satelliteCode);

}
