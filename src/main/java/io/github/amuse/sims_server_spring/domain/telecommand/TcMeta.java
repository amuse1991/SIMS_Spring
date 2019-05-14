package io.github.amuse.sims_server_spring.domain.telecommand;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name="tc_meta")
public class TcMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    @Setter(AccessLevel.NONE) private Long telecommandCode;
    @Setter(AccessLevel.NONE) private String satelliteCode;
    private String telecommandName;

    @Builder
    public TcMeta(String satelliteCode, String telecommandName) {
        this.satelliteCode = satelliteCode;
        this.telecommandName = telecommandName;
    }

    @Override
    public String toString() {
        return "TcMeta{" +
                "telecommandCode=" + telecommandCode +
                ", satelliteCode=" + satelliteCode +
                ", telecommandName='" + telecommandName + '\'' +
                '}';
    }
}
