package com.lei.jvm.google;

import com.lei.jvm.google.retail.SearchClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lei
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2">...</a>
 * GOOGLE_APPLICATION_CREDENTIALS
 * C:\工作文档\BaiduSyncdisk\google\dev-ai-search-user.json
 */
@Slf4j
public class GoogleSearchApp {

    public static void main(String[] args) {
        doSearch();
    }

    private static void doSearch() {
        try {
            SearchClient.doSearch();
        } catch (Exception ex) {
            log.error("doSearch error={}", ex.getMessage(), ex);
        }
    }


}
