package com.lei.jvm.mapdb;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.concurrent.ConcurrentMap;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/4/25 19:40
 */
@Slf4j
public class MapDBTest {

    private static final String FILE_DB = "file.mapdb";


    @Test
    public void testDirectMemory() {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        String key = "Hello";
        String val = "simple";
        map.put(key, val);
        log.info("获取内容={}",map.get(key));
    }


    @Test
    public void testFile() {
        DB db = DBMaker.fileDB(FILE_DB).make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        String key = "something";
        map.put(key, "here");
        map.put(key, "here123456");
        map.put(key, "here1234560000");
        map.put(key, "here123456111111111");
        log.info("文件读取={}", map.get(key));
        db.close();
    }

    @Test
    public void testMixMemory() {
        DB db = DBMaker.fileDB(FILE_DB).fileMmapEnable().make();
        ConcurrentMap<String,String> map = db.hashMap("testMap", Serializer.STRING, Serializer.STRING).createOrOpen();
        String key = "name";
        map.put(key, "xiaobai");
        log.info("获取内容={}",map.get(key));
        db.close();
    }


}
