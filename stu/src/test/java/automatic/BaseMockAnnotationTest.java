package automatic;


import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * @Author leihaoyuan
 * @Date 2021/3/15 13:50
 * @Version 1.0
 * @Description Mocktio单元测试
 */
public class BaseMockAnnotationTest {
    @Before
    public void initEnv() {
        // 初始化Mokito
        MockitoAnnotations.openMocks(this);
    }
}
