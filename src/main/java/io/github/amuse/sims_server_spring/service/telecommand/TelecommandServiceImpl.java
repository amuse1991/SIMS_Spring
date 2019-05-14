package io.github.amuse.sims_server_spring.service.telecommand;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.amuse.sims_server_spring.domain.telecommand.TcMeta;
import io.github.amuse.sims_server_spring.domain.telecommand.TcMetaRepository;
import io.github.amuse.sims_server_spring.domain.telemetry.TmMeta;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;
import io.github.amuse.sims_server_spring.exceptions.json.ParsingJsonToStringException;
import io.github.amuse.sims_server_spring.mongo.dao.telecommand.TcDataRepository;
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
public class TelecommandServiceImpl implements TelecommandService {

    private TcMetaRepository tcMetaRepository;
    private TcDataRepository tcDataRepository;

    @Override
    public TcMetaResDto getMeta(Long tcCode) {
        TcMeta meta = tcMetaRepository.findById(tcCode)
                .orElseThrow(()->new EntityNotFoundException("can't find telecommand meta : " + tcCode));

        return TcMetaResDto.builder()
                .telecommandCode(meta.getTelecommandCode())
                .telecommandName(meta.getTelecommandName())
                .satelliteCode(meta.getSatelliteCode())
                .build();
    }

    @Override
    public String getDataByTaskId(Long tcCode, Double srcTaskId, Double destTaskId) {
        TcMeta meta = tcMetaRepository.findById(tcCode)
                .orElseThrow(()->new EntityNotFoundException("can't find telemetry meta : " + tcCode));
        String name = meta.getTelecommandName();

        List<Document> dataset = tcDataRepository.findDataByTaskId(name,srcTaskId,destTaskId);

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(dataset);
        }catch (JsonProcessingException e){
            throw new ParsingJsonToStringException(dataset); // json advice가 처리
        }
    }

    @Override
    public TcMetaResDto insertMeta(TcMetaReqDto reqForm) {
        TcMeta newMeta = TcMeta.builder()
                .satelliteCode(reqForm.getSatelliteCode())
                .telecommandName(reqForm.getTelecommandName())
                .build();
        tcMetaRepository.save(newMeta);
        return TcMetaResDto.builder()
                .satelliteCode(newMeta.getSatelliteCode())
                .telecommandName(newMeta.getTelecommandName())
                .build();
    }

    @Override
    public TcMetaResDto changeMetaName(Long tmCode, String name) {
        TcMeta meta = tcMetaRepository.findById(tmCode)
                .orElseThrow(()->new EntityNotFoundException("can't find telecommand meta " + tmCode));
        meta.setTelecommandName(name);
        tcMetaRepository.save(meta);
        return TcMetaResDto.builder()
                .satelliteCode(meta.getSatelliteCode())
                .telecommandName(meta.getTelecommandName())
                .build();
    }

    @Override
    public String deleteMeta(Long tmCode) {
        tcMetaRepository.deleteById(tmCode);
        return tmCode.toString();
    }
}
