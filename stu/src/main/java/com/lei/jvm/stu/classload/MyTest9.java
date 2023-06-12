package com.lei.jvm.stu.classload;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/23 13:06
 */
@Slf4j
public class MyTest9 {


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class<?> aClass = Class.forName("com.lei.jvm.stu.classload.Person");
        Person person = (Person)aClass.newInstance();
        person.setName("AAA");
        log.info("反射实例对象={}", JSON.toJSONString(person));
//        for (; ; ) {
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(MyTest9.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback((MethodInterceptor) (object, method, args1, proxy) ->
//                    proxy.invokeSuper(object, args1)
//            );
//            System.out.println("creating...");
//            enhancer.create();
//        }
    }

    private static void testHashCode() throws ClassNotFoundException {
        System.out.println(Class.forName("cn.Lock_LockInterruptibly.classload.MyTest9").hashCode());
        System.out.println(Class.forName("cn.Lock_LockInterruptibly.classload.MyTest9").hashCode());
        System.out.println(Class.forName("cn.Lock_LockInterruptibly.classload.MyTest9").hashCode());
    }

}

class JavaBean{
    private int value = 1;
    public String s = "abc";
    public final static int f = 0x101;

    public void setValue(int v){
        final int temp = 3;
        this.value = temp + v;
    }

    public int getValue(){
        return value;
    }
}
