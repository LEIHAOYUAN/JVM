package com.lei.stu.transaction.rollbackonly;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author leihaoyuan
 * @Date 2021/10/26 17:46
 * @Version 1.0
 * @Description
 * https://www.cnblogs.com/simpledev/p/6406845.html
 */
@Slf4j
@Service
public class AService {

    @Autowired
    private BService bService;

    /**
     * 事务A方法调用事务B方法，如果在同一个事务环境中，B方法异常，A方法try-catch，A方法事务最终仍然无法提交
     * 解决方法：
     * 1. 调用方和被调用方任意一方去除事务环境
     * 2. 事务B方法使用独立事务Propagation.REQUIRES_NEW
     * 3. 事务B方法中的异常自己try-catch处理
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void methodA() {
        log.info("methodAAAA开始执行......");
        try {
            bService.methodB();
        } catch (Exception ex) {
            log.info("捕获到methodB方法异常");
        }
        log.info("methodAAAA执行结束......");
    }

}
