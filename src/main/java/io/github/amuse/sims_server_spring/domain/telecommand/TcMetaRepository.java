package io.github.amuse.sims_server_spring.domain.telecommand;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TcMetaRepository extends JpaRepository<TcMeta,Long> {
    Optional<List<TcMeta>> findAllBySatelliteCode(String satelliteCode);
    Optional<TcMeta> findBySatelliteCode(String satelliteCode);
    boolean existsBySatelliteCode(String satelliteCode);
    void deleteBySatelliteCodeAndTelecommandCode(String satelliteCode, Long tmCode);
}
