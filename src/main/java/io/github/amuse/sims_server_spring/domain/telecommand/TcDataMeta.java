package io.github.amuse.sims_server_spring.domain.telecommand;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="tc_data_meta")
public class TcDataMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TcDataMetaCode",updatable = false)
    private Long tcDataMetaCode;

    @Column(name = "TelecommandCode")
    private Long telecommandCode;

    @Column(name="DataName")
    private String dataName;

    @Column(name="ChartType")
    private String chartType;

    @Column(name="ChartGroup")
    private String chartGroup;

    @Builder
    public TcDataMeta(Long telecommandCode, String dataName, String chartType, String chartGroup) {
        this.telecommandCode = telecommandCode;
        this.dataName = dataName;
        this.chartType = chartType;
        this.chartGroup = chartGroup;
    }

    @Override
    public String toString() {
        return "TcDataMeta{" +
                "tcDataMetaCode=" + tcDataMetaCode +
                ", telecommandCode=" + telecommandCode +
                ", dataName='" + dataName + '\'' +
                ", chartType='" + chartType + '\'' +
                ", chartGroup='" + chartGroup + '\'' +
                '}';
    }
}
