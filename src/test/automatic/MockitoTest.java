package automatic;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * @Author leihaoyuan
 * @Date 2021/3/17 10:17
 * @Version 1.0
 * @Description
 */
public class MockitoTest extends BaseMockTest {

    @Test
    public void test() {
        Mockito.mockStatic(java.lang.System.class);
    }

}
