package io.github.amuse.sims_server_spring.service.telecommand;

import io.github.amuse.sims_server_spring.domain.telecommand.TcMeta;
import io.github.amuse.sims_server_spring.domain.telecommand.TcMetaRepository;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@AllArgsConstructor
public class TelecommandServiceImpl implements TelecommandService {

    private TcMetaRepository tcMetaRepository;

    @Override
    public TcMetaResDto getMeta(String tmCode) {
        return null;
    }

    @Override
    public TcMetaResDto insertMeta(TcMetaReqDto reqForm) {
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
    public TcMetaResDto updateMeta(String satelliteCode, TcMetaReqDto reqForm) {
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
    public String deleteMeta(String satelliteCode, Long tmCode) {
        if(!tcMetaRepository.existsBySatelliteCode(satelliteCode)){
            throw new EntityNotFoundException("can't find satellite " + satelliteCode);
        }
        tcMetaRepository.deleteBySatelliteCodeAndTelecommandCode(satelliteCode, tmCode);
        return satelliteCode;
    }
}
