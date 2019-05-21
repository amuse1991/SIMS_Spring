package io.github.amuse.sims_server_spring.service.socket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimsSocketServiceTest {
    @Autowired
    private SimsSocketService simsSocketService;

    @Test
    public void init은_성공시_데이터배열로응답한다(){
        simsSocketService.init("http://localhost:3500");
    }
}
