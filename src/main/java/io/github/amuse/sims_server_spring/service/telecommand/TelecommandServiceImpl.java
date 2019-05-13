package io.github.amuse.sims_server_spring.service.telecommand;

import io.github.amuse.sims_server_spring.domain.telecommand.TcMeta;
import io.github.amuse.sims_server_spring.domain.telecommand.TcMetaRepository;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class TelecommandServiceImpl implements TelecommandService {

    private TcMetaRepository tcMetaRepository;

    @Override
    public TcMetaResDto getMeta(Long tmCode) {
        return null;
    }

    @Override
    public String getData(Long tcCode, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
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
