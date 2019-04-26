package io.github.amuse.sims_server_spring.domain.satellites;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Satellites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long satelliteCode;

    private String satelliteName;
    private String imgSource;
    private Date launchDate;

    @Builder
    public Satellites(String satelliteName, String imgSource, Date launchDate) {
        this.satelliteName = satelliteName;
        this.imgSource = imgSource;
        this.launchDate = launchDate;
    }

    @Override
    public String toString() {
        return "Satellites{" +
                "satelliteCode=" + satelliteCode +
                ", satelliteName='" + satelliteName + '\'' +
                ", imgSource='" + imgSource + '\'' +
                ", launchDate=" + launchDate +
                '}';
    }
}
