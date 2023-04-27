package com.lei.jvm.mapdb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *  职能描述：mapdb性能测试
 *  @author leihaoyuan
 *  @version 2023/4/26 17:20
 */
@Slf4j
public class MapDBTest {

    private static final int count = 10000;

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
        DB db = DBMaker.tempFileDB().fileDeleteAfterClose().closeOnJvmShutdown().make();
        List<String> filePaths = filePath(db);
        writeData(db);
        readData(db);
        db.close();
        testDelete(filePaths);
    }


    @Test
    public void testFileChannel() {
        DB db = DBMaker.tempFileDB()
                .fileChannelEnable()
                .fileDeleteAfterClose()
                .closeOnJvmShutdown()
                .make();
        List<String> filePaths = filePath(db);
        writeData(db);
        readData(db);
        db.close();
        testDelete(filePaths);
    }


    @Test
    public void testFileMMapSupported() {
        DB db = DBMaker.tempFileDB()
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .fileDeleteAfterClose()
                .closeOnJvmShutdown()
                .make();
        List<String> filePaths = filePath(db);
        writeData(db);
        readData(db);
        db.close();
        testDelete(filePaths);
    }

    @Test
    public void testFileMMap() {
        DB db = DBMaker.tempFileDB()
                .fileMmapEnable()
                .fileMmapPreclearDisable()
                .fileDeleteAfterOpen()
                .fileDeleteAfterClose()
                .closeOnJvmShutdown()
                .closeOnJvmShutdownWeakReference()
                .make();
        List<String> filePaths = filePath(db);
        writeData(db);
        readData(db);
        db.close();
        testDelete(filePaths);
    }

    @Test
    public void testFile() {
        String path = "C:\\Users\\ADMINI~1\\AppData\\Local\\Temp\\mapdb688045750538849335temp";
        new File(path).delete();
        File file = new File(path);
        log.info("文件[{}]是否存在[{}]", path, file.exists());
    }

    private void testDelete(List<String> filePaths) {
        if (CollectionUtils.isEmpty(filePaths)) {
            return;
        }
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists()) {
                log.info("文件[{}]删除结果={}", filePath, file.delete());
            } else {
                log.info("文件[{}]不存在", filePath);
            }
        }
    }

    private List<String> filePath(DB db) {
        List<String> filePathList = Lists.newArrayList();
        for (String filePath : db.getStore().getAllFiles()) {
            log.info("文件路径={}", filePath);
            filePathList.add(filePath);
        }
        return filePathList;
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
