package io.github.amuse.sims_server_spring.domain.orbit;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "orbit")
public class Orbit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DataCode", updatable = false)
    private Long dataCode;

    @Column(name = "SatelliteCode")
    private String satelliteCode;

    @Column(name="UTCTime")
    private Date utcTime;

    @Column(name="Lat")
    private Double lat;

    @Column(name="Long")
    private Double lng;

    @Column(name="Alt")
    private Double alt;

    @Column(name="DateOfEntry")
    private Date dateOfEntry;

    @Builder
    public Orbit(String satelliteCode, Date utcTime, Double lat, Double lng, Double alt, Date dateOfEntry) {
        this.satelliteCode = satelliteCode;
        this.utcTime = utcTime;
        this.lat = lat;
        this.lng = lng;
        this.alt = alt;
        this.dateOfEntry = dateOfEntry;
    }

    @Override
    public String toString() {
        return "Orbit{" +
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
