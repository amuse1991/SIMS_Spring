package io.github.amuse.sims_server_spring.controller.telecommands;

import io.github.amuse.sims_server_spring.domain.telecommand.TcMeta;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaReqDto;
import io.github.amuse.sims_server_spring.dto.telecommand.TcMetaResDto;
import io.github.amuse.sims_server_spring.service.telecommand.TelecommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TelecommandsController {

    private TelecommandService telecommandService;

    @GetMapping("telecommands/metas")
    public TcMetaResDto getMeta(@RequestParam(value = "tc_code")Long tcCode){
        return telecommandService.getMeta(tcCode);
    }

    @PostMapping("telecommands/metas")
    public TcMetaResDto insertMeta(@RequestBody TcMetaReqDto reqForm){
        return telecommandService.insertMeta(reqForm);
    }

    @PutMapping("telecommands/metas/{tcCode}")
    public TcMetaResDto changeMetaName(@PathVariable Long tcCode,
                                       @RequestParam(value = "name") String name){
        return telecommandService.changeMetaName(tcCode,name);
    }

    @DeleteMapping("telecommands/metas/{tcCode}")
    public String deleteMeta(@PathVariable Long tcCode){
        return telecommandService.deleteMeta(tcCode);
    }

    @GetMapping("telecommands/items")
    public ResponseEntity<String> getDataList(@RequestParam(value = "tc_code")Long tcCode,
                                              @RequestParam(value = "src_id")Double srcTaskId,
                                              @RequestParam(value = "dest_id")Double destTaskId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok()
                .headers(headers)
                .body(telecommandService.getDataByTaskId(tcCode,srcTaskId,destTaskId));
    }
}
