package com.lei.jvm.service.mongo.client;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 * @author ryan
 */
public class MongoDBClient implements AutoCloseable {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MongoDBClient(String uri, String dbName, String collectionName) {
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase(dbName);
        collection = database.getCollection(collectionName);
    }

    public void insertDocument(Document doc) {
        collection.insertOne(doc);
    }

    public FindIterable<Document> findDocuments(String key, Object value) {
        return collection.find(Filters.eq(key, value));
    }

    @Override
    public void close() {
        mongoClient.close();
    }

}
