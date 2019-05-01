package io.github.amuse.sims_server_spring.dto.satellite;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatelliteInfoDto {
    private String satelliteCode;
    private String satelliteName;
    private String imgSource;
    private String launchDate;

    @Builder
    public SatelliteInfoDto(String satelliteCode, String satelliteName, String imgSource, String launchDate) {
        this.satelliteCode = satelliteCode;
        this.satelliteName = satelliteName;
        this.imgSource = imgSource;
        this.launchDate = launchDate;
    }

    @Override
    public String toString() {
        return "SatelliteInfoDto{" +
                "satelliteCode='" + satelliteCode + '\'' +
                ", satelliteName='" + satelliteName + '\'' +
                ", imgSource='" + imgSource + '\'' +
                ", launchDate='" + launchDate + '\'' +
                '}';
    }
}
