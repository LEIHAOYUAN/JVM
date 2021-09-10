package com.lei.stu.autocloseable;

import java.io.*;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/3/7 17:48
 */
public class Test {

    /**
     * try-with-resources - the the best way to close resources!
     *
     * @param path
     * @return
     * @throws IOException
     */
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    /**
     * try-with-resources on multiple resources - short and sweet
     *
     * @param src
     * @param dst
     * @throws IOException
     */
    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[200];
            int n;
            while ((n = in.read(buf)) >= 0){
                out.write(buf, 0, n);
            }
        }
    }


}
