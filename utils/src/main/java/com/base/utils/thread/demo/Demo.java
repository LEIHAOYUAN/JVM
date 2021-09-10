package com.base.utils.thread.demo;


import com.base.utils.thread.AbstractAsynchronousHandler;
import com.base.utils.thread.CommonThreadPool;
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
        log.info("测试异步执行任务......");
        CommonThreadPool.execute(new AbstractAsynchronousHandler() {
            @Override
            public Object call() throws Exception {
                try {
                    log.info("异步执行任务......");
                } catch (Exception e) {
                    log.error("异步任务执行失败：{}", e.getMessage(), e);
                }
                return true;
            }
        });
    }

}
