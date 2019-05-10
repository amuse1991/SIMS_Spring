package io.github.amuse.sims_server_spring.dto.orbit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrbitDataDto {
    private Long dataCode;
    private String satelliteCode;
    private Date utcTime;
    private Double lat;
    private Double lng;
    private Double alt;
    private Date dateOfEntry;

    @Builder
    public OrbitDataDto(Long dataCode, String satelliteCode, Date utcTime, Double lat, Double lng, Double alt, Date dateOfEntry) {
        this.dataCode = dataCode;
        this.satelliteCode = satelliteCode;
        this.utcTime = utcTime;
        this.lat = lat;
        this.lng = lng;
        this.alt = alt;
        this.dateOfEntry = dateOfEntry;
    }

    @Override
    public String toString() {
        return "OrbitDataDto{" +
                "dataCode=" + dataCode +
                ", satelliteCode='" + satelliteCode + '\'' +
                ", utcTime=" + utcTime +
                ", lat=" + lat +
                ", lng=" + lng +
                ", alt=" + alt +
                ", dateOfEntry=" + dateOfEntry +
                '}';
    }
}
