package com.lei.jvm.spring;


import com.lei.jvm.spring.config.SyncDataSourceComponent;
import com.lei.jvm.spring.utils.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author leihaoyuan
 * @version 2023/8/15 10:27
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SyncDataSourceComponentTest {

    @Resource
    private SyncDataSourceComponent syncDataSourceComponent;


    @Test
    public void testFindJdbcTemplate() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            ThreadPoolUtil.execute(() -> syncDataSourceComponent.findJdbcTemplate("dev"));
        }
        Thread.sleep(50000);
    }

    @Test
    public void testFindJdbcTemplateMethod() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            ThreadPoolUtil.execute(() -> syncDataSourceComponent.findJdbcTemplateMethod("dev"));
        }
        Thread.sleep(50000);
    }


    @Test
    public void testFindJdbcTemplateWithoutLock() throws Exception {
        for (int i = 0; i < 500; i++) {
            ThreadPoolUtil.execute(() -> {
                try {
                    syncDataSourceComponent.findJdbcTemplateWithoutLock("dev");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        Thread.sleep(50000);
    }

}
