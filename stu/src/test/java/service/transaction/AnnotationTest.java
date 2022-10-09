package service.transaction;

import com.lei.jvm.stu.transaction.rollbackonly.AService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author leihaoyuan
 * @Date 2021/10/26 18:02
 * @Version 1.0
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class AnnotationTest {

    @Resource
    private AService aService;

    @org.junit.Test
    public void test() {
        aService.methodA();
    }


}
