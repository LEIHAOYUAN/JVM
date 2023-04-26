package com.lei.jvm.mapdb;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/4/25 19:40
 *  https://www.jianshu.com/p/b6f43302338e
 */
@Slf4j
public class MapDBTest {

    private static final String FILE_DB = "file.mapdb";

    private static final int count = 10000;

    private static String key = "key";

    @Test
    public void testJvmMemory() {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        writeData(map);
        readData(map);
        db.close();
    }


    @Test
    public void testDirectMemory() {
        DB db = DBMaker.memoryDirectDB().make();
        ConcurrentMap map = db.hashMap("direct_map").createOrOpen();
        writeData(map);
        readData(map);
        db.close();
    }


    @Test
    public void testFile() {
        DB db = DBMaker.fileDB(FILE_DB).fileMmapEnable().make();
        ConcurrentMap<String, String> localMap = db.hashMap("local-map", Serializer.STRING, Serializer.STRING).createOrOpen();
        ConcurrentMap<String, String> remoteMap = db.hashMap("remote-map", Serializer.STRING, Serializer.STRING).createOrOpen();
        String key = "AAA";
        localMap.put(key, "local-map-value");
        remoteMap.put(key, "remote-map-value");
        log.info("本地内容={}", localMap.get(key));
        log.info("远程内容={}", remoteMap.get(key));
        db.close();
    }

    @Test
    public void testFileAutoDelete() {
        DB db = DBMaker.fileDB(FILE_DB).fileMmapEnable().fileDeleteAfterClose().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        writeData(map);
        readData(map);
        db.close();
    }

    @Test
    public void testMixMemory() {
        DB db = DBMaker.fileDB(FILE_DB).fileMmapEnable().make();
        ConcurrentMap<String, String> map = db.hashMap("testMap", Serializer.STRING, Serializer.STRING).createOrOpen();
        writeData(map);
        readData(map);
        db.close();
    }

    private void writeData(Map<String, String> map) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            map.put(key + i, "new-value".concat(key));
        }
        log.info("写[{}]次耗时={}毫秒", count, System.currentTimeMillis() - start);
    }

    private void readData(Map<String, String> map) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            log.info("读取[{}]内容[{}]", key + i, map.get(key + i));
        }
        log.info("读[{}]次耗时={}毫秒", count, System.currentTimeMillis() - start);
    }


}
