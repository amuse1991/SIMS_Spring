package io.github.amuse.sims_server_spring.domain.telemetry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TmMetaRepository extends JpaRepository<TmMeta,Long> {
    Optional<List<TmMeta>> findAllBySatelliteCode(String satelliteCode);
    Optional<TmMeta> findBySatelliteCode(String satelliteCode);
    boolean existsBySatelliteCode(String satelliteCode);
    void deleteBySatelliteCodeAndTelemetryCode(String satelliteCode, Long tmCode);
}
