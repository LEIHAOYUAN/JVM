package com.file;

import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author ryan
 */
@Slf4j
public class FileUtil {


    public static void main(String[] args) {

    }

    private static void test() {
        try {
            // For a simple file system with Unix-style paths and behavior:
            FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
            Path foo = fs.getPath("/foo");
            Files.createDirectory(foo);

            Path hello = foo.resolve("hello.txt"); // /foo/hello.txt
            Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            log.error("异常={}", ex.getMessage(), ex);
        }
    }
}
