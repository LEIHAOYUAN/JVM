package com.lei.jvm.google;

import com.lei.jvm.google.retail.ProductClient;
import lombok.extern.slf4j.Slf4j;

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

    public static void main(String[] args) {
        String productId = "test-20250724-00006";
//        ProductClient.doGet(productId);
//        ProductClient.doImportWithCall(productId);
//        ProductClient.doImportWithFuture(productId);
//        ProductClient.doCreate(productId);
        for (int i = 0; i < 500; i++) {
            ProductClient.doUpdate(productId);
        }
//        SyncGeoHashService.syncLocalInventory(productId);

//        SearchClient.doSearchWithPage();
        try {
            Thread.sleep(8000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
