package io.github.amuse.sims_server_spring.controller.telemetries;

import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class TelemetriesController {
    @GetMapping("telemetries/metas")
    public TmMetaResDto getMeta(@PathVariable String tmCode){
        return null;
    }

    @GetMapping("telemetries/{tmCode}/data")
    public Map<String,Object> getTelemetryData(@PathVariable String tmCode,
                                               @RequestParam(value = "start_date")String startDate,
                                               @RequestParam(value = "end_date")String endDate){
        return null;
    }

}
