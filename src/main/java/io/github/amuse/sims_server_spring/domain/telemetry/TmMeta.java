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
public class TmMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long telemetryCode;

    private Long satelliteCode;
    private String telemetryName;
    private String dataTableName;

    @Builder
    public TmMeta(Long satelliteCode, String telemetryName, String dataTableName) {
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
