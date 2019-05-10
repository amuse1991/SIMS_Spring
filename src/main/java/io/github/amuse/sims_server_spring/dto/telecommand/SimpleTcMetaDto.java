package io.github.amuse.sims_server_spring.dto.telecommand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleTcMetaDto {
    private String tcName;
    private Long tcCode;

    @Builder
    public SimpleTcMetaDto(String tcName, Long tcCode) {
        this.tcName = tcName;
        this.tcCode = tcCode;
    }

    @Override
    public String toString() {
        return "SimpleTcMetaDto{" +
                "tcName='" + tcName + '\'' +
                ", tcCode='" + tcCode + '\'' +
                '}';
    }
}
