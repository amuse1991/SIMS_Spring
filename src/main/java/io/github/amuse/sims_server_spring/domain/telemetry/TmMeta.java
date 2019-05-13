package io.github.amuse.sims_server_spring.domain.telemetry;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name="tm_meta")
public class TmMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    @Setter(AccessLevel.NONE) private Long telemetryCode;
    @Setter(AccessLevel.NONE) private String satelliteCode;
    private String telemetryName;

    @Builder
    public TmMeta(String satelliteCode, String telemetryName) {
        this.satelliteCode = satelliteCode;
        this.telemetryName = telemetryName;
    }

    @Override
    public String toString() {
        return "TmMeta{" +
                "telemetryCode=" + telemetryCode +
                ", satelliteCode=" + satelliteCode +
                ", telemetryName='" + telemetryName + '\'' +
                '}';
    }
}
