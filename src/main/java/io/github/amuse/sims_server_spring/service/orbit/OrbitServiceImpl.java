package io.github.amuse.sims_server_spring.service.orbit;

import io.github.amuse.sims_server_spring.dto.orbit.OrbitDataDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrbitServiceImpl implements OrbitService{
    @Override
    public List<OrbitDataDto> getOrbitData(LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }

    @Override
    public List<OrbitDataDto> getOrbitDataBySatCode(String satelliteCode, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }

    @Override
    public String insertOrbitData(String satelliteCode, OrbitDataDto data) {
        return null;
    }

    @Override
    public String deleteOrbitData(String satelliteCode, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }
}
