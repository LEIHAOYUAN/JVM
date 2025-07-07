package com.lei.jvm.google.retail;

import com.google.api.client.util.Strings;
import com.google.api.core.ApiFuture;
import com.google.cloud.retail.v2.SearchRequest;
import com.google.cloud.retail.v2.SearchResponse;
import com.google.cloud.retail.v2.SearchResponse.SearchResult;
import com.google.cloud.retail.v2.SearchServiceClient;
import com.google.cloud.retail.v2.SearchServiceClient.SearchPagedResponse;
import com.lei.jvm.google.retail.builder.SearchBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.SearchServiceClient">...</a>
 */
@Slf4j
public class SearchClient {

    public static void doSearchWithPage() throws Exception {
        SearchRequest request = SearchBuilder.buildDefaultSearchRequest();
        try (SearchServiceClient searchServiceClient = SearchServiceClient.create()) {
            ApiFuture<SearchPagedResponse> responseApiFuture = searchServiceClient.searchPagedCallable().futureCall(request);
            SearchPagedResponse searchPagedResponse = responseApiFuture.get();
            log.info("查询结果={}", searchPagedResponse.toString());
        }
    }

    public static void doSearch() throws Exception {
        SearchRequest request = SearchBuilder.buildDefaultSearchRequest();
        try (SearchServiceClient searchServiceClient = SearchServiceClient.create()) {
            SearchPagedResponse response = searchServiceClient.search(request);
            for (SearchResult searchResult : response.iterateAll()) {
                log.info("搜索结果={}", searchResult.toString());
            }
        }
    }

    public static void doSearchWithCallable() throws Exception {
        SearchRequest request = SearchBuilder.buildDefaultSearchRequest();
        try (SearchServiceClient searchServiceClient = SearchServiceClient.create()) {
            while (true) {
                SearchResponse response = searchServiceClient.searchCallable().call(request);
                for (SearchResponse.SearchResult element : response.getResultsList()) {
                    // doThingsWith(element);
                }
                String nextPageToken = response.getNextPageToken();
                if (!Strings.isNullOrEmpty(nextPageToken)) {
                    request = request.toBuilder().setPageToken(nextPageToken).build();
                } else {
                    break;
                }
            }
        }
    }

}
