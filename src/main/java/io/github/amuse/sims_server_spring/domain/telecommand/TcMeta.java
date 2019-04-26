package io.github.amuse.sims_server_spring.domain.telecommand;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TcMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long telecommandCode;

    private Long satelliteCode;
    private String telecommandName;
    private String dataTableName;
}
