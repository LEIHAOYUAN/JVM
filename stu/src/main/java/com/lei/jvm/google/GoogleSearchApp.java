package com.lei.jvm.google;

import com.lei.jvm.google.retail.ProductClient;
import com.lei.jvm.google.retail.SearchClient;
import com.lei.jvm.google.retail.geohash.SyncGeoHashService;
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
//        doImport();
//        doSyncLocalInventory();
//        doGetProduct();
        doImportWithCall();
        try {
            Thread.sleep(8000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void doGetProduct() {
        try {
            ProductClient.doGet(null);
        } catch (Exception ex) {
            log.error("doGet error={}", ex.getMessage(), ex);
        }
    }

    private static void doImport() {
        try {
            ProductClient.doImport(null);
        } catch (Exception ex) {
            log.error("doImport error={}", ex.getMessage(), ex);
        }
    }

    private static void doImportWithCall() {
        try {
            ProductClient.doImportWithCall("test-20250703-00001");
        } catch (Exception ex) {
            log.error("doImportWithCall error={}", ex.getMessage(), ex);
        }
    }

    private static void doSyncLocalInventory() {
        try {
            SyncGeoHashService.syncLocalInventory("3498d720-70e1-4edf-9c09-56aa6fac97db");
        } catch (Exception ex) {
            log.error("doSyncLocalInventory error={}", ex.getMessage(), ex);
        }
    }

    private static void doSearch() {
        try {
            SearchClient.doSearchWithPage();
        } catch (Exception ex) {
            log.error("doSearch error={}", ex.getMessage(), ex);
        }
    }


}
