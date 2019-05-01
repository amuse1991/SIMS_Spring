package io.github.amuse.sims_server_spring.service.satellite;

import io.github.amuse.sims_server_spring.domain.satellite.Satellite;
import io.github.amuse.sims_server_spring.domain.satellite.SatelliteRepository;
import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SatelliteServiceImple implements SatelliteService {

    SatelliteRepository satelliteRepository;

    @Override
    public List<SatelliteInfoDto> getSatelliteList(String satellieCode, int startAt, int maxResult) {
        List<SatelliteInfoDto> resList = new ArrayList<>();
        // 특정 위성 검색시
        if(satellieCode != null){
            resList.add(getSatelliteInfo(satellieCode));
            return resList;
        }
        // 조회
        List<Satellite> satellites = satelliteRepository.findAll(); // TODO : 페이징
        // 조회 결과를 Dto에 저장
        satellites.forEach(satItem->{
            resList.add(SatelliteInfoDto.builder()
                .satelliteCode(satItem.getSatelliteCode())
                .satelliteName(satItem.getSatelliteName())
                .imgSource(satItem.getImgSource())
                .launchDate(satItem.getLaunchDate().toString())
                .build());
        });
        return resList;
    }

    @Override
    public SatelliteInfoDto getSatelliteInfo(String satelliteCode) {
        Satellite satellite = satelliteRepository.findById(satelliteCode)
                .orElseThrow(()->new EntityNotFoundException());
        return SatelliteInfoDto.builder()
                .satelliteCode(satellite.getSatelliteCode())
                .satelliteName(satellite.getSatelliteName())
                .imgSource(satellite.getImgSource())
                .build();
    }

    @Override
    public SatelliteInfoDto createSatellite(SatelliteInfoDto reqForm) {
        String satCode = reqForm.getSatelliteCode();

        // 위성코드 중복 검사
        if(satelliteRepository.existsById(satCode)){
            throw new DuplicateKeyException("Duplicate satellite code");
        }

        // 날짜 데이터 파싱
        LocalDateTime launchDate = LocalDateTime.parse(reqForm.getLaunchDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // db 저장
        Satellite newSat = Satellite.builder()
                .satelliteCode(satCode)
                .satelliteName(reqForm.getSatelliteName())
                .imgSource(reqForm.getImgSource())
                .launchDate(launchDate)
                .build();

        satelliteRepository.save(newSat);

        return SatelliteInfoDto.builder()
                .satelliteCode(newSat.getSatelliteCode())
                .satelliteName(newSat.getSatelliteName())
                .imgSource(newSat.getImgSource())
                .launchDate(newSat.getLaunchDate().toString())
                .build();
    }

    @Override
    public SatelliteInfoDto updateSatellite(String satelliteCode, SatelliteInfoDto reqForm) {

        // 조회
        Satellite satellite = satelliteRepository.findById(satelliteCode)
                .orElseThrow(()->new EntityNotFoundException());

        // 날짜 데이터 파싱
        LocalDateTime launchDate = LocalDateTime.parse(reqForm.getLaunchDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 수정
        satellite.setSatelliteName(reqForm.getSatelliteName());
        satellite.setImgSource(reqForm.getImgSource());
        satellite.setLaunchDate(launchDate);
        satelliteRepository.save(satellite);

        return SatelliteInfoDto.builder()
                .satelliteCode(satellite.getSatelliteCode())
                .satelliteName(satellite.getSatelliteName())
                .imgSource(satellite.getImgSource())
                .launchDate(satellite.getLaunchDate().toString())
                .build();
    }

    @Override
    public String deleteSatellite(String satelliteCode) {
        // 위성이 존재하는지 확인
        if(!satelliteRepository.existsById(satelliteCode)){
            throw new EntityNotFoundException();
        }
        satelliteRepository.deleteById(satelliteCode);
        return satelliteCode;
    }
}
