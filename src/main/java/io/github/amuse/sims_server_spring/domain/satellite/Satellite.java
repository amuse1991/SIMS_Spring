package io.github.amuse.sims_server_spring.domain.satellite;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "satellite")
public class Satellite {

    @Id
    @Column(name="SatelliteCode", updatable = false)
    private String satelliteCode;

    @Column(name="SatelliteName")
    private String satelliteName;

    @Column(name="ImgSource")
    private String imgSource;

    @Column(name = "LaunchDate")
    private LocalDateTime launchDate;

    @Builder
    public Satellite(String satelliteCode, String satelliteName, String imgSource, LocalDateTime launchDate) {
        this.satelliteCode = satelliteCode;
        this.satelliteName = satelliteName;
        this.imgSource = imgSource;
        this.launchDate = launchDate;
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "satelliteCode='" + satelliteCode + '\'' +
                ", satelliteName='" + satelliteName + '\'' +
                ", imgSource='" + imgSource + '\'' +
                ", launchDate=" + launchDate +
                '}';
    }
}
