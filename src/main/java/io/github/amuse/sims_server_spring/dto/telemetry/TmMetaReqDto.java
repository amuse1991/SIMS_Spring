package io.github.amuse.sims_server_spring.dto.telemetry;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmMetaReqDto {
    private String satelliteCode;
    private String telemetryName;

    @Builder
    public TmMetaReqDto(String satelliteCode, String telemetryName) {
        this.satelliteCode = satelliteCode;
        this.telemetryName = telemetryName;
    }

    @Override
    public String toString() {
        return "TmMetaReqDto{" +
                "satelliteCode='" + satelliteCode + '\'' +
                ", telemetryName='" + telemetryName + '\'' +
                '}';
    }
}
