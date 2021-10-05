package com.lei.stu.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Author leihaoyuan
 * @Date 2021/10/5 11:18
 * @Version 1.0
 * @Description 事务测试类
 */
@Slf4j
@Service
public class TransactionService {

    @Autowired
    private ApplicationContext context;


    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public boolean test0() {
        // 代理方式事务生效
//        context.getBean(OtherBillService.class).test1();
        // 直接调用情况
//        this.test1();
        // TransactionSynchronizationManager.registerSynchronization连续调用，第二次将不执行
        log.info("test0事务状态----：{}", TransactionSynchronizationManager.isActualTransactionActive());
        context.getBean(TransactionService.class).temp();
        return true;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public boolean test1() {
        log.info("test1事务状态----：{}", TransactionSynchronizationManager.isActualTransactionActive());
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                log.info("监听事务提交状态......");
            }
        });
        return false;
    }


    @Transactional(rollbackFor = Exception.class)
    public void temp() {
        log.info("temp事务状态---：{}", TransactionSynchronizationManager.isActualTransactionActive());
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                context.getBean(TransactionService.class).test1();
            }
        });
    }

}
