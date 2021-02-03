package com.util.thread.demo;

import com.util.thread.AbstractAsynchronousHandler;
import com.util.thread.CommonThreadPool;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/2/3 13:13
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Demo {

    public static void main(String[] args) {
        CommonThreadPool.execute(new AbstractAsynchronousHandler() {
            @Override
            public Object call() throws Exception {
                try {
                    // TODO 异步执行
                } catch (Exception e) {
                    log.error("异步任务执行失败：{}", e.getMessage(), e);
                }
                return true;
            }
        });
    }

}
