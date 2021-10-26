package com.lei.stu.transaction.rollbackonly;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author leihaoyuan
 * @Date 2021/10/26 17:47
 * @Version 1.0
 * @Description
 */
@Slf4j
@Service
public class BService {

    @Transactional(rollbackFor = Exception.class)
    public void methodB(){
        log.info("methodBBBBB开始执行......");
        int i = 1/0;
        log.info("methodBBBBB执行结束......");
    }

}
