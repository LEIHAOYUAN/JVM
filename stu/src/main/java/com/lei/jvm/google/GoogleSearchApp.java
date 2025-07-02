package com.lei.jvm.google;

import com.lei.jvm.google.retail.ProductClient;
import com.lei.jvm.google.retail.SearchClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lei
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
        doCreate();
    }

    private static void doGetProduct() {
        try {
            ProductClient.doGet();
        } catch (Exception ex) {
            log.error("doGet error={}", ex.getMessage(), ex);
        }
    }

    private static void doListProduct() {
        try {
            ProductClient.doList();
        } catch (Exception ex) {
            log.error("doList error={}", ex.getMessage(), ex);
        }
    }

    private static void doPurgeProduct() {
        try {
            ProductClient.doPurge();
        } catch (Exception ex) {
            log.error("doPurge error={}", ex.getMessage(), ex);
        }
    }

    private static void doCreate() {
        try {
            ProductClient.doCreate();
        } catch (Exception ex) {
            log.error("doCreate error={}", ex.getMessage(), ex);
        }
    }

    private static void doImport() {
        try {
            ProductClient.doImport();
        } catch (Exception ex) {
            log.error("doImport error={}", ex.getMessage(), ex);
        }
    }


    private static void doSearch() {
        try {
            SearchClient.doSearch();
        } catch (Exception ex) {
            log.error("doSearch error={}", ex.getMessage(), ex);
        }
    }


}
