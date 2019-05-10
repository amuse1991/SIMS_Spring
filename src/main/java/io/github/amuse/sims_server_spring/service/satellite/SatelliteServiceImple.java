package io.github.amuse.sims_server_spring.service.satellite;

import io.github.amuse.sims_server_spring.domain.satellite.Satellite;
import io.github.amuse.sims_server_spring.domain.satellite.SatelliteRepository;
import io.github.amuse.sims_server_spring.domain.telecommand.TcMeta;
import io.github.amuse.sims_server_spring.domain.telecommand.TcMetaRepository;
import io.github.amuse.sims_server_spring.domain.telemetry.TmMeta;
import io.github.amuse.sims_server_spring.domain.telemetry.TmMetaRepository;
import io.github.amuse.sims_server_spring.dto.satellite.SatelliteInfoDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;
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

    private SatelliteRepository satelliteRepository;
    private TcMetaRepository tcMetaRepository;
    private TmMetaRepository tmMetaRepository;

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
                .orElseThrow(()->new EntityNotFoundException("can't find satellite "+satelliteCode));
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
                .orElseThrow(()->new EntityNotFoundException("can't find satellite "+satelliteCode));

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

    @Override
    public List<TmMetaResDto> getSatlliteTmMeta(String satelliteCode) {
        List<TmMetaResDto> resList = new ArrayList<>();
        List<TmMeta> metas = tmMetaRepository.findAllBySatelliteCode(satelliteCode)
                .orElseThrow(()->new EntityNotFoundException("can't find satellite " + satelliteCode));
        metas.forEach(metaInfo->{
            resList.add(
                    TmMetaResDto.builder()
                            .telemetryCode(metaInfo.getTelemetryCode())
                            .satelliteCode(metaInfo.getSatelliteCode())
                            .telemetryName(metaInfo.getTelemetryName())
                            .dataTableName(metaInfo.getDataTableName())
                            .build()
            );
        });
        return resList;
    }

    @Override
    public TmMetaResDto insertTmMeta(String satelliteCode, TmMetaReqDto reqForm) {
        TmMeta newMeta = TmMeta.builder()
                .satelliteCode(reqForm.getSatelliteCode())
                .telemetryName(reqForm.getTelemetryName())
                .dataTableName(reqForm.getDataTableName())
                .build();
        tmMetaRepository.save(newMeta);
        return TmMetaResDto.builder()
                .satelliteCode(newMeta.getSatelliteCode())
                .telemetryName(newMeta.getTelemetryName())
                .dataTableName(newMeta.getDataTableName())
                .build();
    }

    @Override
    public TmMetaResDto updateTmMeta(String satelliteCode, TmMetaReqDto reqForm) {
        TmMeta meta = tmMetaRepository.findBySatelliteCode(satelliteCode)
                .orElseThrow(()->new EntityNotFoundException("can't find satellite " + satelliteCode));
        meta.setTelemetryName(reqForm.getTelemetryName());
        meta.setDataTableName(reqForm.getDataTableName());
        tmMetaRepository.save(meta);
        return TmMetaResDto.builder()
                .satelliteCode(meta.getSatelliteCode())
                .telemetryName(meta.getTelemetryName())
                .dataTableName(meta.getDataTableName())
                .build();
    }

    @Override
    public String deleteTmMeta(String satelliteCode, Long tmCode) {
        if(!tmMetaRepository.existsBySatelliteCode(satelliteCode)){
            throw new EntityNotFoundException("can't find satellite " + satelliteCode);
        }
        tmMetaRepository.deleteBySatelliteCodeAndTelemetryCode(satelliteCode, tmCode);
        return satelliteCode;
    }

    @Override
    public List<TcMetaResDto> getSatlliteTcMeta(String satelliteCode) {
        List<TcMetaResDto> resList = new ArrayList<>();
        List<TcMeta> metas = tcMetaRepository.findAllBySatelliteCode(satelliteCode)
                .orElseThrow(()->new EntityNotFoundException("can't find satellite " + satelliteCode));
        metas.forEach(metaInfo->{
            resList.add(
                    TcMetaResDto.builder()
                            .telecommandCode(metaInfo.getTelecommandCode())
                            .satelliteCode(metaInfo.getSatelliteCode())
                            .telecommandName(metaInfo.getTelecommandName())
                            .dataTableName(metaInfo.getDataTableName())
                            .build()
            );
        });
        return resList;
    }

    @Override
    public TcMetaResDto insertTcMeta(String satelliteCode, TcMetaReqDto reqForm) {
        TcMeta newMeta = TcMeta.builder()
                .satelliteCode(reqForm.getSatelliteCode())
                .telecommandName(reqForm.getTelecommandName())
                .dataTableName(reqForm.getDataTableName())
                .build();
        tcMetaRepository.save(newMeta);
        return TcMetaResDto.builder()
                .satelliteCode(newMeta.getSatelliteCode())
                .telecommandName(newMeta.getTelecommandName())
                .dataTableName(newMeta.getDataTableName())
                .build();
    }

    @Override
    public TcMetaResDto updateTcMeta(String satelliteCode, TcMetaReqDto reqForm) {
        TcMeta meta = tcMetaRepository.findBySatelliteCode(satelliteCode)
                .orElseThrow(()->new EntityNotFoundException("can't find satellite " + satelliteCode));
        meta.setTelecommandName(reqForm.getTelecommandName());
        meta.setDataTableName(reqForm.getDataTableName());
        tcMetaRepository.save(meta);
        return TcMetaResDto.builder()
                .satelliteCode(meta.getSatelliteCode())
                .telecommandName(meta.getTelecommandName())
                .dataTableName(meta.getDataTableName())
                .build();
    }

    @Override
    public String deleteTcMeta(String satelliteCode, Long tmCode) {
        if(!tcMetaRepository.existsBySatelliteCode(satelliteCode)){
            throw new EntityNotFoundException("can't find satellite " + satelliteCode);
        }
        tcMetaRepository.deleteBySatelliteCodeAndTelecommandCode(satelliteCode, tmCode);
        return satelliteCode;
    }
}
