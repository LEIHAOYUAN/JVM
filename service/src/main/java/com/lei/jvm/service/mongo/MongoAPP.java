package com.lei.jvm.service.mongo;

import com.lei.jvm.service.mongo.client.MongoDBClient;
import com.mongodb.client.FindIterable;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

/**
 * @author ryan
 */
@Slf4j
public class MongoAPP {
    private static final String MONGO_URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "search-data-ingestion";
    private static final String COLLECTION_NAME = "restaurants";

    public static void main(String[] args) {
        try (MongoDBClient mongoClient = new MongoDBClient(MONGO_URI, DB_NAME, COLLECTION_NAME)) {
            FindIterable<Document> documents = mongoClient.findDocuments("_id", "ffda90cf-e470-4d69-b233-c35267b42c2c");
            for (Document document : documents) {
                log.info("result:{}", document.toJson());
            }
        } catch (Exception ex) {
            log.error("异常: {}", ex.getMessage(), ex);
        }
    }
}
