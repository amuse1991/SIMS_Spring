package io.github.amuse.sims_server_spring.dto.telecommand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TcMetaReqDto {
    private String satelliteCode;
    private String telecommandName;
    private String dataTableName;

    @Builder
    public TcMetaReqDto(String satelliteCode, String telecommandName, String dataTableName) {
        this.satelliteCode = satelliteCode;
        this.telecommandName = telecommandName;
        this.dataTableName = dataTableName;
    }

    @Override
    public String toString() {
        return "TcMetaReqDto{" +
                "satelliteCode='" + satelliteCode + '\'' +
                ", telecommandName='" + telecommandName + '\'' +
                ", dataTableName='" + dataTableName + '\'' +
                '}';
    }
}
