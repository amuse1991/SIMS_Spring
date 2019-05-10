package io.github.amuse.sims_server_spring.dto.telemetry;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleTmMetaDto {
    private String tmName;
    private Long tmCode;

    @Builder
    public SimpleTmMetaDto(String tmName, Long tmCode) {
        this.tmName = tmName;
        this.tmCode = tmCode;
    }

    @Override
    public String toString() {
        return "SimpleTmMetaDto{" +
                "tmName='" + tmName + '\'' +
                ", tmCode='" + tmCode + '\'' +
                '}';
    }
}
