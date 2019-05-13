package io.github.amuse.sims_server_spring.service.telecommand;

import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;

import java.time.LocalDateTime;

public interface TelecommandService {
    TcMetaResDto getMeta(Long tmCode);
    String getData(Long tcCode, LocalDateTime startDateTime, LocalDateTime endDateTime);
    TcMetaResDto insertMeta(TcMetaReqDto reqForm);
    TcMetaResDto changeMetaName(Long tmCode, String name);
    String deleteMeta(Long tmCode);
}
