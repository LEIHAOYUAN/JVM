package com.lei.jvm.spring.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.concurrent.ConcurrentMap;

/**
 *  职能描述：mapdb工具包
 *  @author leihaoyuan
 *  @version 2023/2/8 15:09
 *  https://tool.4xseo.com/article/380951.html
 *  https://www.cnblogs.com/shuimutong/p/11438216.html
 */
@Slf4j
@UtilityClass
public class MapDBUtil {

    /**
     * 堆外内存
     */
    public static void putMemoryDB() {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        String key = "Hello";
        String val = "simple";
        map.put(key, val);
        log.info("第1次取值，" + map.get(key));
    }

    public static void getMemoryDB() {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        String key = "Hello";
        log.info("第1次取值，" + map.get(key));
    }


    /**
     * 基于文件
     */
    public static void putFileDB() {
        DB db = DBMaker.fileDB("file.db").make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
        log.info("putFileDB:" + map.get("something"));
        db.close();
    }


    public static void getFileDB() {
        DB db = DBMaker.fileDB("file.db").make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        log.info("getFileDB:" + map.get("something"));
        db.close();
    }

    /**
     * 基于磁盘和内存
     */
    public static void putMemoryFileDB() {
        DB db = DBMaker
                .fileDB("file.db")
                .fileMmapEnable()
                .make();
        ConcurrentMap<String,String> map = db
                .hashMap("testMap", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
        map.put("name", "xiaobai");
        db.close();
    }

    public static void getMemoryFileDB() {
        DB db = DBMaker
                .fileDB("file.db")
                .fileMmapEnable()
                .make();
        ConcurrentMap<String,String> map = db
                .hashMap("testMap", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
        log.info("getMemoryFileDB:"+map.get("name"));
        db.close();
    }



}
