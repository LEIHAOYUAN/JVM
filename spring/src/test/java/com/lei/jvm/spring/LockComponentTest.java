package com.lei.jvm.spring;


import com.lei.jvm.spring.config.LockComponent;
import com.lei.jvm.spring.utils.ThreadPoolUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 加锁测试
 *
 * @author leihaoyuan
 * @version 2023/8/15 10:27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LockComponentTest {

    @Resource
    private LockComponent lockComponent;


    @Test
    public void testFindLockObj() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            ThreadPoolUtil.execute(() -> lockComponent.findLockObj("dev"));
        }
        Thread.sleep(50000);
    }

    @Test
    public void testFindLockMethod() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            ThreadPoolUtil.execute(() -> lockComponent.findLockMethod("dev"));
        }
        Thread.sleep(50000);
    }

    @Test
    public void testFindLockMethodSingleThread() throws InterruptedException {
        ThreadPoolUtil.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    lockComponent.findLockMethod("dev");
                }
            }
        });

        Thread.sleep(50000);
    }


    @Test
    public void testFindWithoutLock() throws Exception {
        for (int i = 0; i < 500; i++) {
            ThreadPoolUtil.execute(() -> {
                lockComponent.findWithoutLock("dev");
            });
        }
        Thread.sleep(50000);
    }

}
