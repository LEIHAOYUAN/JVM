package com.lei.jvm.utils.base.utils.http;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@UtilityClass
public class HttpClientUtil {

    private static final int TIMEOUT = 5000; // 请求超时时间
    private static final int MAX_TOTAL = 200; // 最大连接数
    private static final int MAX_PER_ROUTE = 50; // 每个路由最大连接数

    private static CloseableHttpClient httpClient;

    static {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (x509Certificates, s) -> true).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(MAX_TOTAL);
            cm.setDefaultMaxPerRoute(MAX_PER_ROUTE);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT).setSocketTimeout(TIMEOUT).build();
            httpClient = HttpClientBuilder.create().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).setSSLSocketFactory(sslsf).build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            log.error("初始化SSL异常={}", e.getMessage(), e);
        }
    }

    public static String doGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return execute(httpGet);
    }

    public static String doPost(String url, HttpEntity entity) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    private static String execute(HttpRequestBase request) throws IOException {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }
}