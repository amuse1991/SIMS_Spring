package io.github.amuse.sims_server_spring.service.orbit;

import io.github.amuse.sims_server_spring.dto.orbit.OrbitDataDto;

import java.time.LocalDateTime;
import java.util.List;

public interface OrbitService {
    List<OrbitDataDto> getAllOrbitDataByTerm(LocalDateTime startTime, LocalDateTime endTime);
    List<OrbitDataDto> getOrbitDataBySatCodeAndTerm(String satelliteCode,LocalDateTime startTime,LocalDateTime endTime);
    OrbitDataDto insertOrbitData(String satelliteCode,OrbitDataDto data);
    String deleteOrbitData(String satelliteCode,LocalDateTime startTime,LocalDateTime endTime);
}
