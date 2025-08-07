package com.lei.jvm.google;

import cn.hutool.core.date.StopWatch;
import com.google.cloud.retail.v2.Product;
import com.lei.jvm.google.retail.ProductClient;
import com.lei.jvm.google.retail.SearchClient;
import com.lei.jvm.google.retail.build.CommonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ryan
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2">...</a>
 * GOOGLE_APPLICATION_CREDENTIALS
 * 【搜索】
 * C:\工作文档\BaiduSyncdisk\google\dev-ai-search-user.json
 * D:\工作文档\畅拓科技\BaiduSyncdisk\google\dev-ai-search-user.json
 * 【推荐】
 * C:\工作文档\BaiduSyncdisk\google\dev-search-restaurant-user.json
 * D:\工作文档\畅拓科技\BaiduSyncdisk\google\dev-ai-search-user.json
 */
@Slf4j
public class GoogleSearchApp {

    public static String productId = "test-20250807-00001";

    public static void main(String[] args) {
//        ProductClient.doGet(productId);
//        ProductClient.doImportWithCall(productId);
//        ProductClient.doImportWithFuture(productId);
//        ProductClient.doCreate(productId);
//        ProductClient.doUpdate(productId);
//        SyncGeoHashService.syncLocalInventory(productId);

//        SearchClient.doSearchWithPage();
        testSearchDelay();
        try {
            Thread.sleep(8000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void testImportDelay() {
        ProductClient.doImportWithFuture(productId);
        StopWatch stopWatch = StopWatch.create("monitor");
        stopWatch.start();
        while (true) {
            Product product = ProductClient.doGet(productId);
            if (product != null && product.getTitle().startsWith(CommonBuilder.TITLE_PREFIX)) {
                break;
            }
            LockSupport.parkNanos(1_000_000);
        }
        stopWatch.stop();
        log.info("import生效总耗时=[{}]秒", stopWatch.getTotalTimeSeconds());
    }

    public static void testSearchDelay() {
        ProductClient.doImportWithFuture(productId);
        StopWatch stopWatch = StopWatch.create("monitor");
        stopWatch.start();
        while (true) {
            Product product = SearchClient.doSearch(CommonBuilder.TITLE_PREFIX);
            if (product != null && product.getTitle().startsWith(CommonBuilder.TITLE_PREFIX)) {
                break;
            }
            LockSupport.parkNanos(1_000_000);
        }
        stopWatch.stop();
        log.info("search生效总耗时=[{}]秒", stopWatch.getTotalTimeSeconds());
    }

}
