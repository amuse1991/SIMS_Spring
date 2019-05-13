package io.github.amuse.sims_server_spring.mongo.dao.telecommand;

import com.mongodb.MongoClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TcDataRepository {
    private MongoClient mongoClient;

    // TODO : 패킷 타입으로 조회

    // TODO : 패킷 시퀀스 번호로 조회
}
