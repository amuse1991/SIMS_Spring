package io.github.amuse.sims_server_spring.domain.telemetry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TmMetaRepository extends JpaRepository<TmMeta,Long> {
    List<TmMeta> findAllBySatelliteCode();
}
