package service.transaction;

import com.lei.stu.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author leihaoyuan
 * @Date 2021/9/27 17:12
 * @Version 1.0
 * @Description
 * 【1】@Transactional(propagation = Propagation.NEVER实际可以支持TransactionSynchronizationManager.registerSynchronization的执行
 * 【2】简单来说，在同一个事务中TransactionSynchronizationManager.registerSynchronization只会执行一次，因为同个事务的状态只有一个
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class TransactionServiceAnnotationTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void test() {
        transactionService.test0();
    }

}
