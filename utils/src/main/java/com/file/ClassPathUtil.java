package com.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Iterator;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ryan
 */
public class ClassPathUtil {
    public static String text(String path) {
        return new String(bytes(path), UTF_8);
    }

    public static byte[] bytes(String path) {
        try (InputStream stream = stream(path)) {
            return stream.readAllBytes();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static InputStream stream(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            Iterator<URL> urls = loader.getResources(path).asIterator();
            return openStream(urls, path);
        } catch (IOException e) {
            throw new Error("load resource failed, path=" + path, e);
        }
    }

    static InputStream openStream(Iterator<URL> urls, String path) throws IOException {
        if (urls.hasNext()) {
            URL url = urls.next();
            if (urls.hasNext()) throw new Error("duplicate resources with same name, path=" + path);
            return url.openStream();
        }
        throw new Error("load resource failed, path=" + path);
    }
}
