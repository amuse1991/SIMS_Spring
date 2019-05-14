package io.github.amuse.sims_server_spring.mongo;

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
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MongodbTest {
    @Autowired
    private MongoClient mongoClient;

    @Test
    public void DB접속_성공시_데이터조회가_가능하다(){
        MongoDatabase db = mongoClient.getDatabase("simsdb_mongo_local");
        MongoCollection collection = db.getCollection("test");
        FindIterable findIterable = collection.find();

        try(MongoCursor cursor = findIterable.iterator()) {
            Document res = (Document)cursor.next();
            assertThat(res.get("code"),is(111.0));
        }
    }
}
