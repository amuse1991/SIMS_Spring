package io.github.amuse.sims_server_spring.domain.orbit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrbitRepository extends JpaRepository<Orbit,Long>, CustomOrbitRepository {
    @Query(value = "select o from orbit o where o.utc_time between :startTime and :endTime")
    Optional<List<Orbit>> findOrbitByTerm(@Param("startTime") LocalDateTime start,
                                                  @Param("endTime") LocalDateTime end);
    @Query(value = "select o from orbit o where o.satellite_code = :satCode and (o.utc_time between :startTime and :endTime)")
    Optional<List<Orbit>> findOrbitBySatCodeAndTerm(@Param("satCode") String satelliteCode,
                                                    @Param("startTime") LocalDateTime start,
                                                    @Param("endTime")LocalDateTime end);
    @Query(value = "delete from orbit o where o.satellite_code = :satCode and (o.utc_time between :startTime and :endTime)")
    void deleteOrbitData(@Param("satCode") String satelliteCode,
                         @Param("startTime") LocalDateTime start,
                         @Param("endTime")LocalDateTime end);
}
