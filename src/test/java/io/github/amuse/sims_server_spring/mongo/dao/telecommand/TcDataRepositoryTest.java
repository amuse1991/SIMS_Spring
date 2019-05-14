package io.github.amuse.sims_server_spring.mongo.dao.telecommand;

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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import static com.mongodb.client.model.Filters.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TcDataRepositoryTest {

    @Autowired
    private MongoClient mongoClient;

    @Test
    public void findDataByTaskId는_src와destTaskId로_데이터셋조회_쿼리결과반환(){
        // given
        String metaName = "tc";
        Double srcTaskId = 161.0;
        Double destTaskId = 0.0;
        String databaseName = "simsdb_mongo_local";

        // when
        List<Document> resList = new ArrayList<>();
        MongoDatabase db = mongoClient.getDatabase(databaseName);
        MongoCollection collection = db.getCollection(metaName);

        MongoCursor cursor;
        // 매개변수 값에 따른 처리
        if(srcTaskId == null && destTaskId == null){ // src와 dest 둘다 입력 안된 경우
            throw new IllegalArgumentException("The value of either source task id or test task id must be entered(both are null now)");
        }else if(srcTaskId != null && destTaskId != null){ // src와 dest 둘 다 입력된 경우
            cursor = collection.find(and(eq("Dest_Task_ID",destTaskId),eq("Src_Task_ID",srcTaskId))).iterator();
        }else{ // src와 dest 둘 중 하나만 입력된 경우
            if(srcTaskId != null){ // src만 입력된 경우
                cursor = collection.find(eq("Src_Task_ID",srcTaskId)).iterator();
            }else{ // dest만 입력된 경우
                cursor = collection.find(eq("Dest_Task_ID",destTaskId)).iterator();
            }
        }
        try{
            while (cursor.hasNext()){
                Document doc = (Document)cursor.next();
                resList.add(doc);
            }
        }finally {
            cursor.close();
        }

        assertThat(resList.size(),is(425)); // 2019-05-14 기준 src_task_id 161, dest_task_id 0 인 TC데이터 수는 425개
    }
}
