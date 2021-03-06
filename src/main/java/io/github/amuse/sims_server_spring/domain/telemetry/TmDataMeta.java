package io.github.amuse.sims_server_spring.domain.telemetry;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="tm_data_meta")
public class TmDataMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long tmDataMetaCode;
    private Long telemetryCode;
    private String dataName;
    private String chartType;
    private String chartGroup;

    @Builder
    public TmDataMeta(Long telemetryCode, String dataName, String chartType, String chartGroup) {
        this.telemetryCode = telemetryCode;
        this.dataName = dataName;
        this.chartType = chartType;
        this.chartGroup = chartGroup;
    }

    @Override
    public String toString() {
        return "TmDataMeta{" +
                "tmDataMetaCode=" + tmDataMetaCode +
                ", telemetryCode=" + telemetryCode +
                ", dataName='" + dataName + '\'' +
                ", chartType='" + chartType + '\'' +
                ", chartGroup='" + chartGroup + '\'' +
                '}';
    }
}
