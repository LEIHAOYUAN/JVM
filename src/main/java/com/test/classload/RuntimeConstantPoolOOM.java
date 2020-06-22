package com.test.classload;

public class RuntimeConstantPoolOOM{
    public static void main(String[] args) throws ClassNotFoundException {

         String str1 = new StringBuilder("计算机").append("软件").toString();
         System.out.println(str1.intern() == str1);


         String str2_0 = new StringBuilder("ja").append("va").toString();
         String str2_1 = "java";
         System.out.println("str2_0.intern() == str2_0:"+str2_0.intern() == str2_0);
         System.out.println(str2_0.intern() == str2_1);
         System.out.println(str2_0 == str2_1);

         Class.forName("cn.Lock_LockInterruptibly.classload.RuntimeConstantPoolOOM");

        Class<? extends String> aClass = str2_0.getClass();

        Class<RuntimeConstantPoolOOM> runtimeConstantPoolOOMClass = RuntimeConstantPoolOOM.class;

        System.out.println(int.class == Integer.TYPE);

    }
}