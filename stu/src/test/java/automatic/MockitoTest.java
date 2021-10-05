package automatic;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

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

    @Test
    public void testSpy() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ArrayList spyList = spy(arrayList);

        doReturn(100).when(spyList).size();

        System.out.println(spyList.size());

        spyList.add(0);
        spyList.add(1);

        System.out.println(spyList.size());
        System.out.println(spyList.toString());

        verify(spyList).add(0);
        verify(spyList).add(1);
    }

    /**
     * 当需要部分mock，部分调用真是对象方法的时候，使用spy
     * 不过，需要注意的是，对 spy 对象进行 stub 时尽量使用 doReturn.when(spyObj).someMethod() 的方式，
     * 因为当使用 when(spyObj.someMethod()).thenReturn() 方式时 someMethod 相当于在 stub 之前被调用了一次，
     * 而 spy 对象调用未被 stub 的方法时，会直接调用原对象的相应方法，容易造成异常。
     */
    @Test
    public void testSpyException() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ArrayList spyList = spy(arrayList);

        when(spyList.get(0)).thenReturn("call get");
    }

}
