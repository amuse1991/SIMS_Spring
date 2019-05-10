package io.github.amuse.sims_server_spring.dto.telemetry;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmMetaResDto {
    private Long telemetryCode;
    private String satelliteCode;
    private String telemetryName;
    private String dataTableName;

    @Builder
    public TmMetaResDto(Long telemetryCode, String satelliteCode, String telemetryName, String dataTableName) {
        this.telemetryCode = telemetryCode;
        this.satelliteCode = satelliteCode;
        this.telemetryName = telemetryName;
        this.dataTableName = dataTableName;
    }

    @Override
    public String toString() {
        return "TmMetaResDto{" +
                "telemetryCode=" + telemetryCode +
                ", satelliteCode='" + satelliteCode + '\'' +
                ", telemetryName='" + telemetryName + '\'' +
                ", dataTableName='" + dataTableName + '\'' +
                '}';
    }
}
