package io.github.amuse.sims_server_spring.domain.telemetry;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TmDataMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long telemetryCode;
    private String dataName;
    private String chartType;
    private String chartGroup;

    @Builder
    public TmDataMeta(String dataName, String chartType, String chartGroup) {
        this.dataName = dataName;
        this.chartType = chartType;
        this.chartGroup = chartGroup;
    }

    @Override
    public String toString() {
        return "TmDataMeta{" +
                "telemetryCode=" + telemetryCode +
                ", dataName='" + dataName + '\'' +
                ", chartType='" + chartType + '\'' +
                ", chartGroup='" + chartGroup + '\'' +
                '}';
    }
}
