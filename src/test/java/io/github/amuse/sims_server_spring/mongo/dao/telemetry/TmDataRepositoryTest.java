package io.github.amuse.sims_server_spring.mongo.dao.telemetry;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TmDataRepositoryTest {

    @Autowired
    private MongoClient mongoClient;

    @Test
    public void findDataByNameAndTerm은_이름과시간으로_데이터셋조회_쿼리결과반환(){
        // given
        String metaName = "fcs";
        // fcs 테스트 데이터 2016-06-03 00시 부터 03시까지 있음
        String start = LocalDateTime.of(2016,6,3,0,0,0)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:MM"));
        String end  = LocalDateTime.of(2016,6,3,1,0,0) // 01시까지 테스트(90개 조회)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:MM"));

        // when
        List<Document> resList = new ArrayList<>();
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
