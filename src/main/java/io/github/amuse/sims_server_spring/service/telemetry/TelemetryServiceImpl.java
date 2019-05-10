package io.github.amuse.sims_server_spring.service.telemetry;

import io.github.amuse.sims_server_spring.domain.telemetry.TmMeta;
import io.github.amuse.sims_server_spring.domain.telemetry.TmMetaRepository;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@AllArgsConstructor
public class TelemetryServiceImpl implements TelemetryService{
    private TmMetaRepository tmMetaRepository;
    @Override
    public TmMetaResDto getMeta(String tmCode) {
        return null;
    }

    @Override
    public TmMetaResDto insertMeta(TmMetaReqDto reqForm) {
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
    public TmMetaResDto updateMeta(String satelliteCode, TmMetaReqDto reqForm) {
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
    public String deleteMeta(String satelliteCode, Long tmCode) {
        if(!tmMetaRepository.existsBySatelliteCode(satelliteCode)){
            throw new EntityNotFoundException("can't find satellite " + satelliteCode);
        }
        tmMetaRepository.deleteBySatelliteCodeAndTelemetryCode(satelliteCode, tmCode);
        return satelliteCode;
    }
}
