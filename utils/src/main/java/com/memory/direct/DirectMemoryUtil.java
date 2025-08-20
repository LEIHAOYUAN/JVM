package com.memory.direct;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @author ryan
 * java 直接内存使用
 * @see <a href="https://blog.csdn.net/qq_37362891/article/details/141569415">...</a>
 */
@Slf4j
public class DirectMemoryUtil {
    private static final int BUFFER = 1024 * 1024 * 1024;//1GB

    public static void main(String[] args) {

    }

    private static void test() {
        try {
            //直接分配本地内存空间
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
            System.out.println("直接内存分配完毕,请求指示!");
            Scanner scanner = new Scanner(System.in);
            scanner.next();
            System.out.println("直接内存开始释放!");
            byteBuffer = null;
            System.gc();
            scanner.next();
        } catch (Exception ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }

}
