package io.github.amuse.sims_server_spring.service.telecommand;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TelecommandServiceTest {
    @Autowired
    private TelecommandService telecommandService;

    @Test
    public void getDataByTeskId는_taskId로TC데이터를조회한다_성공시null이아닌값반환(){
        //given
        Long tcCode = 1L;
        String srcTaskId = "161";
        String destTaskId = "0";

        // when
//        String jsonData = telecommandService.getDataByTaskId(tcCode,srcTaskId,destTaskId);

        // then
//        assertNotNull(jsonData);
    }
}
