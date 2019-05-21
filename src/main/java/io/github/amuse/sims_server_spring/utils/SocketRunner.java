package io.github.amuse.sims_server_spring.utils;

import io.github.amuse.sims_server_spring.service.socket.SimsSocketService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SocketRunner implements CommandLineRunner {
    /*
        Spring 실행 시점에 SIMS Server와 dummy server 사이의 소켓 연결 시도
        연결이 확인되면 데이터 수신
     */
    private SimsSocketService simsSocketService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Establishing connection to the dummy server");
        simsSocketService.init("http://localhost:3500");
    }
}
