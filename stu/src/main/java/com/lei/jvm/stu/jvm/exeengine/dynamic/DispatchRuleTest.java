package com.lei.jvm.stu.jvm.exeengine.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @Author leihaoyuan
 * @Date 2022/1/28 14:39
 * @Version 1.0
 * @Description 方法分派规则
 */
public class DispatchRuleTest {

    public static void main(String[] args) {
        (new DispatchRuleTest().new Son()).thinking();
    }


    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        void thinking() {
            // 请读者在这里填入适当的代码（不能修改其他地方的代码）实现调用祖父类的thinking()方法，打印"i am grandfather"
            // 【方式1】使用方法句柄实现
            try {
                MethodType mt = MethodType.methodType(void.class);
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class,"thinking", mt, GrandFather.class);
                mh.invoke(this);
            } catch (Throwable e) {
                System.out.println(e);
            }
        }
    }




}
