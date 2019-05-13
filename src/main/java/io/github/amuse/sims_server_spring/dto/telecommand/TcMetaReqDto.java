package io.github.amuse.sims_server_spring.dto.telecommand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TcMetaReqDto {
    private String satelliteCode;
    private String telecommandName;

    @Builder
    public TcMetaReqDto(String satelliteCode, String telecommandName) {
        this.satelliteCode = satelliteCode;
        this.telecommandName = telecommandName;
    }

    @Override
    public String toString() {
        return "TcMetaReqDto{" +
                "satelliteCode='" + satelliteCode + '\'' +
                ", telecommandName='" + telecommandName + '\'' +
                '}';
    }
}
