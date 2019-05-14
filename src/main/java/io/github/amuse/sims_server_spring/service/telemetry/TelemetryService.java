package io.github.amuse.sims_server_spring.service.telemetry;

import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;

import java.time.LocalDateTime;

public interface TelemetryService {
    TmMetaResDto getMeta(Long tmCode);
    String getDataByDateTime(Long tmCode, LocalDateTime startDateTime, LocalDateTime endDateTime);
    TmMetaResDto insertMeta(TmMetaReqDto reqForm);
    TmMetaResDto changeMetaName(Long tmCode, String name);
    String deleteMeta(Long tmCode);
}
