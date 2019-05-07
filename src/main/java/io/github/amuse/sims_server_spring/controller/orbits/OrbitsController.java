package io.github.amuse.sims_server_spring.controller.orbits;

import io.github.amuse.sims_server_spring.dto.orbit.OrbitDataDto;
import io.github.amuse.sims_server_spring.service.orbit.OrbitServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@AllArgsConstructor
public class OrbitsController {
    private int DEFAULT_TERM_MIN = 30; // end time 입력되지 않은 경우 디폴트로 +30분 동안의 결과를 조회한다.
    private OrbitServiceImpl orbitService;

    @GetMapping("/orbits")
    public List<OrbitDataDto> getOrbitData(@RequestParam(value = "start") String startTime,
                                           @RequestParam(value = "end",required = false) String endTime){

        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        LocalDateTime end = getEndTime(start,endTime);

        return orbitService.getAllOrbitDataByTerm(start,end);
    }

    @GetMapping("/orbits/{satelliteCode}")
    public List<OrbitDataDto> getOrbitDataBySatCode(@PathVariable String satelliteCode,
                                                    @RequestParam(value = "start") String startTime,
                                                    @RequestParam(value = "end",required = false) String endTime){

        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = getEndTime(start,endTime);
        return orbitService.getOrbitDataBySatCodeAndTerm(satelliteCode,start,end);
    }

    @PostMapping("/orbits/{satelliteCode}")
    public OrbitDataDto insertOrbitData(@PathVariable String satelliteCode, @RequestBody OrbitDataDto data){
        return orbitService.insertOrbitData(satelliteCode,data);
    }

    @DeleteMapping("/orbits/{satelliteCode}")
    public String deleteOrbitData(@PathVariable String satelliteCode,
                                  @RequestParam(value = "start") String startTime,
                                  @RequestParam(value = "end") String endTime){
        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = getEndTime(start,endTime);
        return orbitService.deleteOrbitData(satelliteCode,start,end);
    }

    private LocalDateTime getEndTime(LocalDateTime startTime, String endTime){
        if(endTime == null){
            return startTime.plusMinutes(DEFAULT_TERM_MIN);
        }else{
            return LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
