package com.lei.jvm.spring.moudle.mongo.service;

import com.lei.jvm.spring.moudle.mongo.dto.User;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  职能描述：mongo操作
 *  @author leihaoyuan
 *  @version 2022/10/20 9:53
 */
@Slf4j
@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> listUser() {
        return mongoTemplate.findAll(User.class, "user");
    }

    public void createUser() {
        mongoTemplate.insert(new User(), "user");
    }

    public void updateUser(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(user.getId()));

        Update update = new Update();
        update.set("name", user.getName());
        update.set("password", user.getPassword());
        update.set("address", user.getAddress());
        update.set("last_update_time", new Date());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "user");
    }

    public void deleteUser(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class, "user");
    }

    /**
     * 批量操作
     *
     * @param userList
     */
    public void batchUpsert(List<User> userList) {
        // 构造数据
        List<Pair<Query, Update>> updateList = new ArrayList<>(userList.size());
        userList.forEach(user -> {
            Query query = new Query(new Criteria("_id").is(user.getId()));
            Update update = new Update();
            update.set("name", user.getName());
            Pair<Query, Update> updatePair = Pair.of(query, update);
            updateList.add(updatePair);
        });
        // 执行批量操作
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, "user");
        operations.upsert(updateList);
        BulkWriteResult result = operations.execute();
        log.info("upsertReceive result, insertCount: {}, matchCount: {}, modifyCount:{}", result.getInsertedCount(), result.getMatchedCount(), result.getModifiedCount());
    }


}
