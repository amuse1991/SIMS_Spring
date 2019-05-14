package io.github.amuse.sims_server_spring.mongo.dao.telecommand;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Repository
@AllArgsConstructor
public class TcDataRepository {
    private MongoClient mongoClient;

    public List<Document> findDataByTaskId(String metaName, Double srcTaskId, Double destTaskId){
        String databaseName = "simsdb_mongo_local";

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

        return resList;
    }
}
