package io.github.amuse.sims_server_spring.service.orbit;

import io.github.amuse.sims_server_spring.domain.orbit.Orbit;
import io.github.amuse.sims_server_spring.domain.orbit.OrbitRepository;
import io.github.amuse.sims_server_spring.dto.orbit.OrbitDataDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrbitServiceImpl implements OrbitService{

    private OrbitRepository orbitRepository;

    // 전체 위성에 대한 궤도 정보 조회
    @Override
    public List<OrbitDataDto> getAllOrbitDataByTerm(LocalDateTime startTime, LocalDateTime endTime) {
        List<OrbitDataDto> resList = new ArrayList<>();
        List<Orbit> orbits = orbitRepository.findOrbitByTerm(startTime, endTime) // start time 부터 end time 까지의 궤도정보 조회
                .orElseThrow(()->new EntityNotFoundException("can't find orbit data between "+startTime+" and "+endTime));
        orbits.forEach(orbitItem->{
            resList.add(
                    OrbitDataDto.builder()
                            .satelliteCode(orbitItem.getSatelliteCode())
                            .utcTime(orbitItem.getUtcTime())
                            .lat(orbitItem.getLat())
                            .lng(orbitItem.getLng())
                            .alt(orbitItem.getAlt())
                            .dateOfEntry(orbitItem.getDateOfEntry())
                            .build()
            );
        });
        return resList;
    }

    // 특정 위성에 대한 궤도 정보 조회
    @Override
    public List<OrbitDataDto> getOrbitDataBySatCodeAndTerm(String satelliteCode, LocalDateTime startTime, LocalDateTime endTime) {
        List<OrbitDataDto> resList = new ArrayList<>();
        List<Orbit> orbits = orbitRepository.findOrbitBySatCodeAndTerm(satelliteCode,startTime, endTime) // start time 부터 end time 까지의 궤도정보 조회
                .orElseThrow(()->new EntityNotFoundException("can't find orbit data satellite code: "+satelliteCode+", start time: "+startTime+", end time:"+endTime));
        orbits.forEach(orbitItem->{
            resList.add(
                    OrbitDataDto.builder()
                            .satelliteCode(orbitItem.getSatelliteCode())
                            .utcTime(orbitItem.getUtcTime())
                            .lat(orbitItem.getLat())
                            .lng(orbitItem.getLng())
                            .alt(orbitItem.getAlt())
                            .dateOfEntry(orbitItem.getDateOfEntry())
                            .build()
            );
        });
        return resList;
    }

    @Override
    public OrbitDataDto insertOrbitData(String satelliteCode, OrbitDataDto data) {
        Orbit newOrbit = Orbit.builder()
                .satelliteCode(data.getSatelliteCode())
                .utcTime(data.getUtcTime())
                .lat(data.getLat())
                .lng(data.getLng())
                .alt(data.getAlt())
                .dateOfEntry(data.getDateOfEntry())
                .build();
        orbitRepository.save(newOrbit);

        return OrbitDataDto.builder()
                .satelliteCode(newOrbit.getSatelliteCode())
                .utcTime(newOrbit.getUtcTime())
                .lat(newOrbit.getLat())
                .lng(newOrbit.getLng())
                .alt(newOrbit.getAlt())
                .dateOfEntry(newOrbit.getDateOfEntry())
                .build();
    }

    @Override
    public String deleteOrbitData(String satelliteCode, LocalDateTime startTime, LocalDateTime endTime) {
        orbitRepository.deleteOrbitData(satelliteCode,startTime,endTime);
        return satelliteCode;
    }
}
