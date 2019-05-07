package io.github.amuse.sims_server_spring.domain.orbit;

import io.github.amuse.sims_server_spring.dto.orbit.OrbitDataDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomOrbitRepository {
    List<OrbitDataDto> deleteOrbitDataBySatCodeAndTerm(String satelliteCode, LocalDateTime startTime, LocalDateTime endTime);
}
