package com.lei.jvm.google.retail;

import com.google.api.client.util.Lists;
import com.google.api.core.ApiFuture;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.SearchRequest;
import com.google.cloud.retail.v2.SearchRequest.Builder;
import com.google.cloud.retail.v2.SearchResponse;
import com.google.cloud.retail.v2.SearchServiceClient;
import com.google.cloud.retail.v2.SearchServiceClient.SearchPagedResponse;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.SearchServiceClient">...</a>
 */
@Slf4j
public class SearchClient {

    /**
     * 过滤条件：
     * attributes.line_item_type: ANY("ITEM") AND availability: ANY("IN_STOCK")
     *
     * @param title
     * @return
     */
    public static List<Product> doSearch(String productId) {
        try {
            SearchServiceClient searchServiceClient = SearchServiceClient.create();
            SearchRequest request = SearchRequest.newBuilder()
                .setPlacement(CommonBuilder.buildPlacement())
                .setBranch(CommonBuilder.buildBranch())
                .setVisitorId("test-a")
                .setQuery(productId)
                .setFilter("availability: ANY(\"OUT_OF_STOCK\")")
                //.setFilter(("title: ANY(\"" + title + "\")"))
                .setPageSize(120)
                .build();
            SearchPagedResponse response = searchServiceClient.searchPagedCallable().call(request);
            List<SearchResponse.SearchResult> resultsList = response.getPage().getResponse().getResultsList();
            if (ListUtil.isEmpty(resultsList)) {
                return Lists.newArrayList();
            }
            return resultsList.stream().map(SearchResponse.SearchResult::getProduct).toList();
        } catch (Exception ex) {
            log.error("doSearchError={}", ex.getMessage(), ex);
        }
        return null;
    }

    public static void doSearchWithPage() {
        try {
            SearchServiceClient searchServiceClient = SearchServiceClient.create();
            Builder pageBuilder = SearchRequest.newBuilder()
                .setPlacement(CommonBuilder.buildPlacement())
                .setBranch(CommonBuilder.buildBranch())
                .setVisitorId("test-a")
                .setQuery("burger")
                //.setFilter(("brands: ANY(\"Alanza\")"))
                .setPageSize(120);

            while (true) {
                ApiFuture<SearchPagedResponse> responseFuture = searchServiceClient.searchPagedCallable().futureCall(pageBuilder.build());
                SearchPagedResponse response = responseFuture.get();
                if (StringUtils.isBlank(response.getNextPageToken())) {
                    break;
                }
                pageBuilder.setPageToken(response.getNextPageToken());
                log.info("分页查询结果={}", response.getNextPageToken());
            }
        } catch (Exception ex) {
            log.error("doSearchWithPage error={}", ex.getMessage(), ex);
        }
    }
}
