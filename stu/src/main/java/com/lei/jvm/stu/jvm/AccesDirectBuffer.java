package com.lei.jvm.stu.jvm;

import java.nio.ByteBuffer;

/**
 * @Description java直接内存和对内存操作耗时比较
 * @Author leihaoyuan
 * @Date 2020/6/28 13:49
 * 结论:
 * 1、在申请内存空间时，堆空间的速度远远快于直接内存。
 * 2、直接内存的访问速度比堆内存的访问速度快一个数量级
 * 由此可以得出结论：直接内存适合申请次数较少、访问较频繁的场合。如果需要频繁申请内存空间，则并不适合使用直接内存。
 */
public class AccesDirectBuffer {

    public void directAccess(){
        long startTime = System.currentTimeMillis();
        ByteBuffer  b = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            b.flip();

            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("【directBuffer】："+ (endTime - startTime));
    }

    public void bufferAccess(){
        long startTime = System.currentTimeMillis();
        ByteBuffer  b = ByteBuffer.allocate(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                b.putInt(j);
            }
            b.flip();

            for (int j = 0; j < 99; j++) {
                b.getInt();
            }
            b.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("【heapBuffer】："+ (endTime - startTime));
    }

    public static void main(String[] args) {
        AccesDirectBuffer buffer = new AccesDirectBuffer();
        buffer.bufferAccess();
        buffer.directAccess();
        System.out.println("热身代码");
        buffer.bufferAccess();
        buffer.directAccess();
    }



}
