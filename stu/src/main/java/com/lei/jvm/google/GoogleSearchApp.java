package com.lei.jvm.google;

import cn.hutool.core.date.StopWatch;
import com.google.api.gax.rpc.FailedPreconditionException;
import com.google.api.gax.rpc.UnknownException;
import com.google.cloud.retail.v2.Product;
import com.lei.jvm.google.retail.ProductClient;
import com.lei.jvm.google.retail.SearchClient;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.geohash.SyncGeoHashService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeoutException;
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

    public static String productId = "test-20250928-0008";

    public static void main(String[] args) {
//        ProductClient.doGetById(productId);
//        ProductClient.doImportWithCall(productId);
//        ProductClient.doImportWithOperation(productId);
//        ProductClient.doCreate(productId);
//        ProductClient.doUpdate(productId);
//        ProductClient.doDelete(productId);
//        SyncGeoHashService.doSyncLocalInventory(productId);
        SyncGeoHashService.doSyncLocalInventoryLimit(productId);

//        SearchClient.doSearchWithPage();
//        testSearchDelay();
        try {
            Thread.sleep(8000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNotifyException(Exception ex) {
        if (ex == null) {
            return false;
        }
        if (ex instanceof UnknownException || ex.getCause() instanceof UnknownException) {
            return false;
        }
        if (ex instanceof TimeoutException || ex.getCause() instanceof TimeoutException) {
            return false;
        }
        if (ex instanceof FailedPreconditionException || ex.getCause() instanceof FailedPreconditionException) {
            return false;
        }
        return true;
    }


    public static void testImportDelay() {
        ProductClient.doImportWithMetadata(productId);
        StopWatch stopWatch = StopWatch.create("monitor");
        stopWatch.start();
        while (true) {
            Product product = ProductClient.doGetById(productId);
            if (product != null && product.getTitle().startsWith(CommonBuilder.TITLE_PREFIX)) {
                break;
            }
            LockSupport.parkNanos(1_000_000);
        }
        stopWatch.stop();
        log.info("import生效总耗时=[{}]秒", stopWatch.getTotalTimeSeconds());
    }

    public static void testSearchDelay() {
        StopWatch stopWatch = StopWatch.create("monitor");
        stopWatch.start();
        outerLoop:
        while (true) {
            List<Product> productList = SearchClient.doSearch(productId);
            for (Product product : productList) {
                if (product != null && product.getName().contains(productId)) {
                    break outerLoop;
                }
            }
            LockSupport.parkNanos(1_000_000);
        }
        stopWatch.stop();
        log.info("search生效总耗时=[{}]秒", stopWatch.getTotalTimeSeconds());
    }

}
