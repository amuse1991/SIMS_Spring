package io.github.amuse.sims_server_spring.dto.telecommand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TcMetaResDto {
    private Long telecommandCode;
    private String satelliteCode;
    private String telecommandName;
    private String dataTableName;

    @Builder
    public TcMetaResDto(Long telecommandCode, String satelliteCode, String telecommandName, String dataTableName) {
        this.telecommandCode = telecommandCode;
        this.satelliteCode = satelliteCode;
        this.telecommandName = telecommandName;
        this.dataTableName = dataTableName;
    }

    @Override
    public String toString() {
        return "TcMetaResDto{" +
                "telecommandCode=" + telecommandCode +
                ", satelliteCode='" + satelliteCode + '\'' +
                ", telecommandName='" + telecommandName + '\'' +
                ", dataTableName='" + dataTableName + '\'' +
                '}';
    }
}
