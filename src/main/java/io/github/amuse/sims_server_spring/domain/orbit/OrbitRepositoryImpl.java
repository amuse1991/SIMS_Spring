package io.github.amuse.sims_server_spring.domain.orbit;

import io.github.amuse.sims_server_spring.dto.orbit.OrbitDataDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class OrbitRepositoryImpl implements CustomOrbitRepository {

    private EntityManager entityManager;

    @Override
    public List<OrbitDataDto> deleteOrbitDataBySatCodeAndTerm(String satelliteCode, LocalDateTime startTime, LocalDateTime endTime) {

        return null;
    }
}
