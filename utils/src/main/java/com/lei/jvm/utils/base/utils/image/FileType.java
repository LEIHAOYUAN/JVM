package com.lei.jvm.utils.base.utils.image;

import java.io.FileInputStream;


public class FileType {

    /**
     * 字节转换为字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        FileInputStream is = new FileInputStream("C:\\工作文档\\00000Docker\\二维码测试\\testbar.jpg");
        byte[] b = new byte[3];
        is.read(b, 0, b.length);
        String xxx = bytesToHexString(b);
        xxx = xxx.toUpperCase();
        System.out.println("头文件是：" + xxx);
        String ooo = TypeDict.checkType(xxx);
        System.out.println("后缀名是：" + ooo);

    }


}