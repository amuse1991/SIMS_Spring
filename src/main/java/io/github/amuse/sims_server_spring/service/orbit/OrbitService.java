package io.github.amuse.sims_server_spring.service.orbit;

import io.github.amuse.sims_server_spring.dto.orbit.OrbitDataDto;

import java.time.LocalDateTime;
import java.util.List;

public interface OrbitService {
    List<OrbitDataDto> getOrbitData(LocalDateTime startTime, LocalDateTime endTime);
    List<OrbitDataDto> getOrbitDataBySatCode(String satelliteCode,LocalDateTime startTime,LocalDateTime endTime);
    String insertOrbitData(String satelliteCode,OrbitDataDto data);
    String deleteOrbitData(String satelliteCode,LocalDateTime startTime,LocalDateTime endTime);
}
