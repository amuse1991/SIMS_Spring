package io.github.amuse.sims_server_spring.domain.telecommand;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="tc_meta")
public class TcMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TelecommandCode",updatable = false)
    private Long telecommandCode;

    @Column(name="SatelliteCode")
    private String satelliteCode;

    @Column(name="TelecommandName")
    private String telecommandName;

    @Column(name="DataTableName")
    private String dataTableName;

    @Builder
    public TcMeta(String satelliteCode, String telecommandName, String dataTableName) {
        this.satelliteCode = satelliteCode;
        this.telecommandName = telecommandName;
        this.dataTableName = dataTableName;
    }

    @Override
    public String toString() {
        return "TcMeta{" +
                "telecommandCode=" + telecommandCode +
                ", satelliteCode=" + satelliteCode +
                ", telecommandName='" + telecommandName + '\'' +
                ", dataTableName='" + dataTableName + '\'' +
                '}';
    }
}
