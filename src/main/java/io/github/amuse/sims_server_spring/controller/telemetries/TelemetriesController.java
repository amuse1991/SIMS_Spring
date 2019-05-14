package io.github.amuse.sims_server_spring.controller.telemetries;

import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telemetry.TmMetaResDto;
import io.github.amuse.sims_server_spring.service.telemetry.TelemetryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@AllArgsConstructor
public class TelemetriesController {

    private TelemetryServiceImpl telemetryService;

    @GetMapping("telemetries/metas")
    public TmMetaResDto getMeta(@RequestParam(value = "tm_code") Long tmCode){
        return telemetryService.getMeta(tmCode);
    }

    @PostMapping("telemetries/metas")
    public TmMetaResDto insertMeta(@RequestBody TmMetaReqDto reqForm){
        return telemetryService.insertMeta(reqForm);
    }

    @PutMapping("telemetries/metas/{tmCode}")
    public TmMetaResDto changeMetaName(@PathVariable Long tmCode,
                                   @RequestParam(value = "name") String name){
        return telemetryService.changeMetaName(tmCode,name);
    }

    @DeleteMapping("telemetries/metas/{tmCode}")
    public String deleteMeta(@PathVariable Long tmCode){
        return telemetryService.deleteMeta(tmCode);
    }

    @GetMapping("telemetries/items")
    public ResponseEntity<String> getDataList(@RequestParam(value = "tm_code")Long tmCode,
                                              @RequestParam(value = "start_date_time") String sDateTime,
                                              @RequestParam(value = "end_date_time") String eDateTime){

        // TODO : custom argument resolver로 처리하도록 리팩토링
        LocalDateTime sDate = LocalDateTime.parse(sDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm"));
        LocalDateTime eDate = LocalDateTime.parse(eDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok()
                .headers(headers)
                .body(telemetryService.getDataByDateTime(tmCode,sDate,eDate));
    }
}
