package io.github.amuse.sims_server_spring.service.telemetry;

import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;

public interface TelemetryService {
    TmMetaResDto getMeta(String tmCode);
    TmMetaResDto insertMeta(TmMetaReqDto reqForm);
    TmMetaResDto updateMeta(String satelliteCode, TmMetaReqDto reqForm);
    String deleteMeta(String satelliteCode, Long tmCode);
}
