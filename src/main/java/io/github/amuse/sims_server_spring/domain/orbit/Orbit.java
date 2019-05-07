package io.github.amuse.sims_server_spring.domain.orbit;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "orbit")
public class Orbit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long dataCode;
    private String satelliteCode;
    private Date utcTime;
    private Double lat;
    private Double lng;
    private Double alt;
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
