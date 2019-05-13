package io.github.amuse.sims_server_spring.service.telemetry;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TelemetryServiceTest {
    @Autowired
    private TelemetryServiceImpl telemetryService;

    @Test
    public void getDate_isTm데이터를_조회한다_성공시returns_null이아닌값(){
        // given
        Long tmCode = 1L;
        LocalDateTime startDateTime = LocalDateTime.of(2016,6,3,0,0);
        LocalDateTime endDateTime = LocalDateTime.of(2016,6,3,1,0);

        // when
        String jsonData = telemetryService.getData(tmCode,startDateTime,endDateTime);

        // then
        assertNotNull(jsonData);
    }
}
