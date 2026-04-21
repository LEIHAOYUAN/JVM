package com.lei.jvm.utils.base.utils.file;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文件MD5工具类，用于对比两个文件内容是否完全一致
 * @author ryan
 */
@Slf4j
public class FileMD5Util {

    /**
     * 计算文件的MD5值
     * @param filePath 文件全路径
     * @return MD5十六进制字符串
     */
    public static String getFileMD5(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            DigestInputStream dis = new DigestInputStream(fis, md);
            byte[] buffer = new byte[8192];
            while (dis.read(buffer) != -1) {
                // 读取文件内容，MD5自动更新
            }
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException("计算文件MD5失败: " + filePath, e);
        }
    }

    /**
     * 对比两个文件内容是否完全一致（基于MD5）
     * @param filePath1 文件1全路径
     * @param filePath2 文件2全路径
     * @return true表示内容一致，false表示不一致
     */
    public static boolean isFileEqual(String filePath1, String filePath2) {
        return getFileMD5(filePath1).equals(getFileMD5(filePath2));
    }

    public static void main(String[] args) {
        String file1 = "C:\\工作文档\\BaiduSyncdisk\\AI工具\\Gemini\\workspace\\对比json\\json-1.txt";
        String file2 = "C:\\工作文档\\BaiduSyncdisk\\AI工具\\Gemini\\workspace\\对比json\\json-2.txt";
        System.out.println("文件MD5-1: " + getFileMD5(file1));
        System.out.println("文件MD5-2: " + getFileMD5(file2));
        System.out.println("文件内容是否一致: " + isFileEqual(file1, file2));
    }
}
