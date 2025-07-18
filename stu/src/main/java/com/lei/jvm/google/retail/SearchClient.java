package com.lei.jvm.google.retail;

import com.google.api.core.ApiFuture;
import com.google.cloud.retail.v2.SearchRequest;
import com.google.cloud.retail.v2.SearchRequest.Builder;
import com.google.cloud.retail.v2.SearchServiceClient;
import com.google.cloud.retail.v2.SearchServiceClient.SearchPagedResponse;
import com.lei.jvm.google.retail.build.CommonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.SearchServiceClient">...</a>
 */
@Slf4j
public class SearchClient {

    public static void doSearchWithPage() throws Exception {
        Builder pageBuilder = SearchRequest.newBuilder()
                .setPlacement(CommonBuilder.buildPlacement())
                .setBranch(CommonBuilder.buildBranch())
                .setVisitorId("test-a")
                .setQuery("burger")
                //.setFilter(("brands: ANY(\"Alanza\")"))
                .setPageSize(120);
        SearchServiceClient searchServiceClient = SearchServiceClient.create();
        while (true) {
            ApiFuture<SearchPagedResponse> responseFuture = searchServiceClient.searchPagedCallable().futureCall(pageBuilder.build());
            SearchPagedResponse response = responseFuture.get();
            if (StringUtils.isBlank(response.getNextPageToken())) {
                break;
            }
            pageBuilder.setPageToken(response.getNextPageToken());
            log.info("分页查询结果={}", response.getNextPageToken());
        }
    }
}
