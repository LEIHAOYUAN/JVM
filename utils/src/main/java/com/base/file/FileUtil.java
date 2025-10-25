package com.base.file;

import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.createDirectories;
import static java.nio.file.Files.getLastModifiedTime;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Files.walkFileTree;

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

    public static String text(Path file) {
        return new String(bytes(file), UTF_8);
    }

    public static byte[] bytes(Path file) {
        try {
            return readAllBytes(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            log.debug("bytes, file={}", file);
        }
    }

    public static void createDir(Path directory) {
        try {
            createDirectories(directory);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void deleteDir(Path directory) {
        try {
            walkFileTree(directory, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException e) {
                    delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            log.debug("deleteDir, directory={}", directory);
        }
    }

    public static Path tempFile() {
        String tempDir = System.getProperty("java.io.tmpdir");
        return Paths.get(tempDir + "/" + UUID.randomUUID() + ".tmp");
    }

    public static Path tempDir() {
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Paths.get(tempDir + "/" + UUID.randomUUID());
        createDir(path);
        return path;
    }

    public static void delete(Path file) {
        try {
            java.nio.file.Files.delete(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static long size(Path file) {
        try {
            return java.nio.file.Files.size(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static Instant lastModified(Path file) {
        try {
            return getLastModifiedTime(file).toInstant();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
