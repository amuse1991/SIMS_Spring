package io.github.amuse.sims_server_spring.service.telemetry;

import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;

public interface TelemetryService {
    TmMetaResDto getMeta(String tmCode);
}
