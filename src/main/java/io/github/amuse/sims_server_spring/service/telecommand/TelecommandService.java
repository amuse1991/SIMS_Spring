package io.github.amuse.sims_server_spring.service.telecommand;

import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;

public interface TelecommandService {
    TcMetaResDto getMeta(String tmCode);
    TcMetaResDto insertMeta(TcMetaReqDto reqForm);
    TcMetaResDto updateMeta(String satelliteCode, TcMetaReqDto reqForm);
    String deleteMeta(String satelliteCode, Long tmCode);
}
