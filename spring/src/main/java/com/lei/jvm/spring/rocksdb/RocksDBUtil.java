package com.lei.jvm.spring.rocksdb;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;

/**
 *  职能描述：rocksdb工具包
 *  @author leihaoyuan
 *  @version 2023/4/27 9:48
 */
@Slf4j
@UtilityClass
public class RocksDBUtil {

    static {
        RocksDB.loadLibrary();
    }

    private static RocksDB rocksDB;
    private static String path = "/opt/myRocksDB";

    public static void main(String[] args) throws Exception {

        Options options = new Options();
        options.setCreateIfMissing(true);
        rocksDB = RocksDB.open(options, path);

        rocksDB.put("felixzh_key".getBytes(), "felixzh_value".getBytes());

        byte[] bytes = rocksDB.get("felixzh_key".getBytes());

        log.info("get: key is felixzh_key, value is " + new String(bytes));

        RocksIterator iter = rocksDB.newIterator();

        for (iter.seekToFirst(); iter.isValid(); iter.next()) {
            log.info("iter key: " + new String(iter.key()) + ",iter value: " + new String(iter.value()));
        }

    }






}
