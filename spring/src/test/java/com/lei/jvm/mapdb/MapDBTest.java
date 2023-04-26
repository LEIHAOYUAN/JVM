package com.lei.jvm.mapdb;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.Map;

/**
 *  职能描述：mapdb性能测试
 *  @author leihaoyuan
 *  @version 2023/4/26 17:20
 */
@Slf4j
public class MapDBTest {

    private static final String FILE_DB = "tips.cache";

    private static final int count = 100000;

    private static final String key = "key";

    private static final String MAP = "test-map";

    @Test
    public void testJvmMemory() {
        DB db = DBMaker.memoryDB().make();
        writeData(db);
        readData(db);
        db.close();
    }


    @Test
    public void testDirectMemory() {
        DB db = DBMaker.memoryDirectDB().make();
        writeData(db);
        readData(db);
        db.close();
    }

    @Test
    public void testFileNormal() {
        DB db = DBMaker.fileDB(FILE_DB).fileDeleteAfterClose().closeOnJvmShutdown().make();
        writeData(db);
        readData(db);
        db.close();
    }


    @Test
    public void testFileChannel() {
        DB db = DBMaker.fileDB(FILE_DB).fileChannelEnable().fileDeleteAfterClose().closeOnJvmShutdown().make();
        writeData(db);
        readData(db);
        db.close();
    }


    @Test
    public void testFileMMapSupported() {
        DB db = DBMaker.fileDB(FILE_DB).fileMmapEnableIfSupported().fileDeleteAfterClose().closeOnJvmShutdown().make();
        writeData(db);
        readData(db);
        db.close();
    }

    @Test
    public void testFileMMap() {
        DB db = DBMaker.fileDB(FILE_DB).fileMmapEnable().make();
        writeData(db);
        readData(db);
        db.close();
    }

    private void writeData(DB db) {
        Map<String, String> map = db.hashMap(MAP, Serializer.STRING, Serializer.STRING).createOrOpen();
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            map.put(key + i, "new-value".concat(key));
        }
        log.info("写[{}]次耗时={}毫秒", count, System.currentTimeMillis() - start);
    }

    private void readData(DB db) {
        Map<String, String> map = db.hashMap(MAP, Serializer.STRING, Serializer.STRING).createOrOpen();
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            log.info("读取[{}]内容[{}]", key + i, map.get(key + i));
        }
        log.info("读[{}]次耗时={}毫秒", count, System.currentTimeMillis() - start);
    }


}
