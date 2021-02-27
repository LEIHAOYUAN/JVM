package com.stu.classload;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/23 13:06
 */
public class MyTest9 {


    public static void main(String[] args) throws ClassNotFoundException {


        System.out.println(Class.forName("cn.Lock_LockInterruptibly.classload.MyTest9").hashCode());
        System.out.println(Class.forName("cn.Lock_LockInterruptibly.classload.MyTest9").hashCode());
        System.out.println(Class.forName("cn.Lock_LockInterruptibly.classload.MyTest9").hashCode());
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
