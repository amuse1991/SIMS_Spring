package io.github.amuse.sims_server_spring.db;

import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataMongoTest
public class MongodbTest {
    @Autowired
    private MongoClient mongoClient;

    @Test
    @Ignore
    public void DB접속_테스트(){
        MongoDatabase db = mongoClient.getDatabase("simsdb_mongo_local");
        MongoCollection collection = db.getCollection("test");
        FindIterable findIterable = collection.find();

        try(MongoCursor cursor = findIterable.iterator()) {
            Document res = (Document)cursor.next();
            assertThat(res.get("code"),is(111.0));
        }
    }

    @Test
    public void findDataByNameAndTerm_is이름과_시간으로_데이터셋조회_returns쿼리결과데이터셋(){
        // given
        String metaName = "fcs";
        // fcs 테스트 데이터 2016-06-03 00시 부터 03시까지 있음
        String start = LocalDateTime.of(2016,6,3,0,0,0)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:MM"));
        String end  = LocalDateTime.of(2016,6,3,1,0,0) // 01시까지 테스트(90개 조회)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:MM"));
        List<Document> resList = new ArrayList<>();
        // when

        MongoDatabase db = mongoClient.getDatabase("simsdb_mongo_local");
        MongoCollection collection = db.getCollection(metaName);

        try(MongoCursor cursor = collection.find(and(gt("Time", start), lte("Time", end))).iterator()){
            while(cursor.hasNext()){
                Document doc = (Document)cursor.next();
                resList.add(doc);
            }
        }

        // 209-05-13 기준 테스트 데이터 셋 200개에서, 2016-06-03 00:00:00 ~ 03:00:00에 해당하는 데이터는 90개
        assertThat(resList.size(),is(90));
    }
}
