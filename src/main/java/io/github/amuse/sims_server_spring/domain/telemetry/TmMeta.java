package io.github.amuse.sims_server_spring.domain.telemetry;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="tm_meta")
public class TmMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TelemetryCode", updatable = false)
    private Long telemetryCode;

    @Column(name="SatelliteCode")
    private String satelliteCode;

    @Column(name="TelemetryName")
    private String telemetryName;

    @Column(name="DataTableName")
    private String dataTableName;

    @Builder
    public TmMeta(String satelliteCode, String telemetryName, String dataTableName) {
        this.satelliteCode = satelliteCode;
        this.telemetryName = telemetryName;
        this.dataTableName = dataTableName;
    }

    @Override
    public String toString() {
        return "TmMeta{" +
                "telemetryCode=" + telemetryCode +
                ", satelliteCode=" + satelliteCode +
                ", telemetryName='" + telemetryName + '\'' +
                ", dataTableName='" + dataTableName + '\'' +
                '}';
    }
}
