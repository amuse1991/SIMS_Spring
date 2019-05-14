package io.github.amuse.sims_server_spring.service.telemetry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.amuse.sims_server_spring.domain.telemetry.TmMeta;
import io.github.amuse.sims_server_spring.domain.telemetry.TmMetaRepository;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;
import io.github.amuse.sims_server_spring.exceptions.json.ParsingJsonToStringException;
import io.github.amuse.sims_server_spring.mongo.dao.telemetry.TmDataRepository;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TelemetryServiceImpl implements TelemetryService{

    private TmMetaRepository tmMetaRepository;
    private TmDataRepository tmDataRepository;

    @Override
    public TmMetaResDto getMeta(Long tmCode) {
        TmMeta meta = tmMetaRepository.findById(tmCode)
                .orElseThrow(()->new EntityNotFoundException("can't find telemetry meta : " + tmCode));

        return TmMetaResDto.builder()
                .telemetryCode(meta.getTelemetryCode())
                .telemetryName(meta.getTelemetryName())
                .satelliteCode(meta.getSatelliteCode())
                .build();
    }

    @Override
    public String getDataByDateTime(Long tmCode, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        // tm 이름 확인
        TmMeta meta = tmMetaRepository.findById(tmCode)
                .orElseThrow(()->new EntityNotFoundException("can't find telemetry meta : " + tmCode));
        String name = meta.getTelemetryName();

        // 데이터 조회
        List<Document> dataset = tmDataRepository.findDataByNameAndTerm(name,startDateTime,endDateTime);

        // json 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(dataset);
        }catch (JsonProcessingException e){
            throw new ParsingJsonToStringException(dataset); // json advice가 처리
        }
    }

    @Override
    public TmMetaResDto insertMeta(TmMetaReqDto reqForm) {
        TmMeta newMeta = TmMeta.builder()
                .satelliteCode(reqForm.getSatelliteCode())
                .telemetryName(reqForm.getTelemetryName())
                .build();
        tmMetaRepository.save(newMeta);
        return TmMetaResDto.builder()
                .satelliteCode(newMeta.getSatelliteCode())
                .telemetryName(newMeta.getTelemetryName())
                .build();
    }

    @Override
    public TmMetaResDto changeMetaName(Long tmCode, String name) {
        TmMeta meta = tmMetaRepository.findById(tmCode)
                .orElseThrow(()->new EntityNotFoundException("can't find telemetry meta info : " + tmCode));
        meta.setTelemetryName(name);
        tmMetaRepository.save(meta);
        return TmMetaResDto.builder()
                .satelliteCode(meta.getSatelliteCode())
                .telemetryName(meta.getTelemetryName())
                .build();
    }

    @Override
    public String deleteMeta(Long tmCode) {
        tmMetaRepository.deleteById(tmCode);
        return tmCode.toString();
    }
}
