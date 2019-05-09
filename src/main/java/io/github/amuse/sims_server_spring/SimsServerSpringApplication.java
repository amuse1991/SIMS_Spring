package io.github.amuse.sims_server_spring;

import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@SpringBootApplication
public class SimsServerSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimsServerSpringApplication.class, args);
    }

}
