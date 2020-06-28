package com.test.jvm;

import java.nio.ByteBuffer;

/**
 * @Description java直接内存和对内存操作耗时比较
 * @Author leihaoyuan
 * @Date 2020/6/28 13:49
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
