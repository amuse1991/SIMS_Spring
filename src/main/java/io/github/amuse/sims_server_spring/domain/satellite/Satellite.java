package io.github.amuse.sims_server_spring.domain.satellite;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "satellite")
public class Satellite {

    @Id
    @Column(updatable = false)
    @Setter(AccessLevel.NONE) private String satelliteCode;
    private String satelliteName;
    private String imgSource;
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
