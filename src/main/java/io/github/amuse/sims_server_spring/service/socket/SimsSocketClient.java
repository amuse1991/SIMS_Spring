package io.github.amuse.sims_server_spring.service.socket;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class SimsSocketClient  {

    public void init(String uri){
        try{
            Socket socket = IO.socket(uri);
            socket.connect(); // 연결 요청
            socket.on("conn_ack", new Emitter.Listener() {
                // event handler
                @Override
                public void call(Object... args) {
                    System.out.println("successfully connected");
                    System.out.println("connection id : " + args[0].toString());

                    System.out.println("Check if satellite data is being received");
                    socket.emit("check_receive"); // 현재 관제 서버가 위성 데이터를 수신중인지 확인
                }
            });
            socket.on("check_ack", new Emitter.Listener() { // 관제 서버가 위성 데이터를 수신중인 경우
                @Override
                public void call(Object... args) {
                    System.out.println("ok");
                    socket.emit("init_receive"); // 수신할 데이터 타입 요청
                }
            }).on("init_ack", new Emitter.Listener() { // 데이터 타입 수신
                @Override
                public void call(Object... args) {
                    for(Object arg : args){
                        System.out.println(arg.toString());
                    }
                    socket.emit("ready"); // 데이터 요청
                }
            }).on("send", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println(args[0].toString());
                }
            });
            socket.on("check_nak", new Emitter.Listener() { // 관제 서버가 위성 데이터를 수신중이지 않은 경우
                @Override
                public void call(Object... args) {
                    System.out.println("Satellite data not being received");
                    System.out.println("disconnecting..."); // 연결 해제
                    socket.disconnect();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
