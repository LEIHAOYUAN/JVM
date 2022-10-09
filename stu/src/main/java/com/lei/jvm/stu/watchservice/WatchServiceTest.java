package com.lei.jvm.stu.watchservice;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * @Description java WatchService demo
 * https://www.jianshu.com/p/f20aba1ecae6
 * @Author leihaoyuan
 * @Date 2020/3/21 15:32
 */
public class WatchServiceTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();

        String filePath = "D:/aa";

        Paths.get(filePath).register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

        while (true) {
            WatchKey key = watchService.take();
            List<WatchEvent<?>> watchEvents = key.pollEvents();
            for (WatchEvent<?> event : watchEvents) {
                if (StandardWatchEventKinds.ENTRY_CREATE == event.kind()) {
                    System.out.println("创建：[" + filePath + "/" + event.context() + "]");
                }
                if (StandardWatchEventKinds.ENTRY_MODIFY == event.kind()) {
                    System.out.println("修改：[" + filePath + "/" + event.context() + "]");
                }
                if (StandardWatchEventKinds.ENTRY_DELETE == event.kind()) {
                    System.out.println("删除：[" + filePath + "/" + event.context() + "]");
                }

            }
            key.reset();
        }
    }
}
