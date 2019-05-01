package io.github.amuse.sims_server_spring.dto.satellite;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatelliteInfoReqDto {
    private String satelliteName;
    private String imgSource;
    private String launchDate;

    @Builder
    public SatelliteInfoReqDto(String satelliteName, String imgSource, String launchDate) {
        this.satelliteName = satelliteName;
        this.imgSource = imgSource;
        this.launchDate = launchDate;
    }

    @Override
    public String toString() {
        return "SatelliteInfoReqDto{" +
                "satelliteName='" + satelliteName + '\'' +
                ", imgSource='" + imgSource + '\'' +
                ", launchDate='" + launchDate + '\'' +
                '}';
    }
}
