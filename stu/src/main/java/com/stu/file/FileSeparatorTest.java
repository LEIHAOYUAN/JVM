package com.stu.file;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author leihaoyuan
 * @Date 2021/8/25 9:44
 * @Version 1.0
 * @Description 文件分隔符测试
 */
@Slf4j
public class FileSeparatorTest {


    public static void main(String[] args) {

    }

    private static void testWindow() {
        File file = new File("E:\\demo\\aa.txt");
    }

    private static void testLinux() {
        File file = new File("/demo/aa.txt");
    }

    private static void commonPath() {
        File file = new File("E:" + File.separator + "demo" + File.separator, "aa.txt");
    }

    private static void validFile(File file) {
        if (null != file && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    private static void writeFile(File file, String data) {
        if (null != file) {
            try (Writer writer = new FileWriter(file)) {
                writer.write(data);
            } catch (Exception exception) {
                log.error("异常：{}", exception.getMessage(), exception);
            }
        }
    }

    private static void writeFileOld(File file, String data) {
        Writer out = null;
        if (null != file) {
            try {
                out = new FileWriter(file);
                out.write(data);
            } catch (Exception e) {
                log.error("异常：{}", e.getMessage(), e);
            } finally {
                if (null != out) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        log.error("关闭流异常：{}", e.getMessage(), e);
                    }
                }
            }
        }
    }


    private static void readFileWithBuffered(File file) {
        if (null == file) {
            return;
        }
        try (FileInputStream fis = new FileInputStream(file); BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))) {
            String line = null;
            while (null != (line = br.readLine())) {
                log.info("读取内容：{}", line);
            }
        } catch (Exception ex) {
            log.error("异常：{}", ex.getMessage(), ex);
        }
    }

    private static void readFileWithStream(File file) {
        if (null == file) {
            return;
        }
        Charset c = StandardCharsets.UTF_8;
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {
            List<String> rows = stream.collect(Collectors.toList());
            for (String row : rows) {
                log.info("读取row内容：{}", row);
            }
        } catch (Exception ex) {
            log.error("异常：{}", ex.getMessage(), ex);
        }

    }


    private static void deleteFile(File file) {
        if (null != file && file.exists()) {
            file.delete();
        }
    }

}
