package io.github.amuse.sims_server_spring.mongo.dao.telemetry;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

@Repository
@AllArgsConstructor
public class TmDataRepository {
    private MongoClient mongoClient;

    public List<Document> findDataByNameAndTerm(String metaName, LocalDateTime startDateTime, LocalDateTime endDateTime){
        List<Document> resList = new ArrayList<>();
        String start = startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:MM"));
        String end = endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:MM"));

        MongoDatabase db = mongoClient.getDatabase("simsdb_mongo_local");
        MongoCollection collection = db.getCollection(metaName);

        try(MongoCursor cursor = collection.find(and(gt("Time", start), lte("Time", end))).iterator()){
            while(cursor.hasNext()){
                Document doc = (Document)cursor.next();
                resList.add(doc);
            }
        }

        return resList;
    }

}
