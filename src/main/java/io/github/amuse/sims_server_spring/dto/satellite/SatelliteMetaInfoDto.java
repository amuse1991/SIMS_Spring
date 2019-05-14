package io.github.amuse.sims_server_spring.dto.satellite;

import io.github.amuse.sims_server_spring.dto.telecommand.SimpleTcMetaDto;
import io.github.amuse.sims_server_spring.dto.telemetry.SimpleTmMetaDto;
import io.github.amuse.sims_server_spring.utils.enumset.MetaTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SatelliteMetaInfoDto {
    private String satelliteCode;
    private List<SimpleTmMetaDto> tmInfo;
    private List<SimpleTcMetaDto> tcInfo;

    public SatelliteMetaInfoDto(String satelliteCode) {
        this.satelliteCode = satelliteCode;
        this.tmInfo = new ArrayList<>();
        this.tcInfo = new ArrayList<>();
    }

    @Builder
    public SatelliteMetaInfoDto(String satelliteCode, List<SimpleTmMetaDto> tmInfo, List<SimpleTcMetaDto> tcInfo) {
        this.satelliteCode = satelliteCode;
        this.tmInfo = tmInfo;
        this.tcInfo = tcInfo;
    }

    // tm, tc Info 리스트에 메타 정보 추가
    public void addMeta(MetaTypes metaType, String metaName, Long metaCode){
        if(metaType == MetaTypes.TELEMETRY){
            this.tmInfo.add(SimpleTmMetaDto.builder().
                    tmName(metaName).
                    tmCode(metaCode).
                    build()
            );
        }else{
            this.tcInfo.add(SimpleTcMetaDto.builder()
                    .tcName(metaName)
                    .tcCode(metaCode)
                    .build());
        }
    }

    @Override
    public String toString() {
        return "SatelliteMetaInfoDto{" +
                "satelliteCode='" + satelliteCode + '\'' +
                ", tmInfo=" + tmInfo +
                ", tcInfo=" + tcInfo +
                '}';
    }
}
