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
import com.lei.jvm.google.retail.geohash.SyncGeoHashService;
import com.lei.jvm.google.retail.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

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
                // .setFilter("availability: ANY(\"OUT_OF_STOCK\")")
                //.setFilter(("title: ANY(\"" + title + "\")"))
                .setOrderBy("inventory(place_id,attributes.key)")
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

    //.setQuery("burger")
    //.setFilter(("brands: ANY(\"Alanza\")"))
    //.setFilter("localInventories.place_id: ANY(\"dr5renw\")")
    //.setOrderBy("localInventories.attributes.distance asc")
    //.setCondition("inventories.attributes.distance")
    public static void doSearchWithPage() {
        String placeId = "dr5renw";
        SearchRequest.BoostSpec.ConditionBoostSpec boostSpec0 = SearchRequest.BoostSpec.ConditionBoostSpec.newBuilder()
            .setCondition("(attributes.restaurant_brand_category: ANY(\"WONDER_HDR\"))")
            .setBoost(0.5f)
            .build();
        SearchRequest.BoostSpec.ConditionBoostSpec boostSpec1 = SearchRequest.BoostSpec.ConditionBoostSpec.newBuilder()
            .setCondition("(inventory(" + placeId + ",attributes.distance) >= 0 AND inventory(" + placeId + ",attributes.distance) <=8)")
            .setBoost(0.5f)
            .build();
        /*SearchRequest.BoostSpec.ConditionBoostSpec boostSpec2 = SearchRequest.BoostSpec.ConditionBoostSpec.newBuilder()
            .setCondition("(inventory(" + placeId + ",attributes.distance) > 3 AND inventory(" + placeId + ",attributes.distance) <=8)")
            .setBoost(0.8f)
            .build();
        SearchRequest.BoostSpec.ConditionBoostSpec boostSpec3 = SearchRequest.BoostSpec.ConditionBoostSpec.newBuilder()
            .setCondition("(inventory(" + placeId + ",attributes.distance) > 8 AND inventory(" + placeId + ",attributes.distance) <=15)")
            .setBoost(0.5f)
            .build();
        SearchRequest.BoostSpec.ConditionBoostSpec boostSpec4 = SearchRequest.BoostSpec.ConditionBoostSpec.newBuilder()
            .setCondition("(inventory(" + placeId + ",attributes.distance) > 15)")
            .setBoost(0.01f)
            .build();*/
        SearchRequest.BoostSpec boostSpec = SearchRequest.BoostSpec.newBuilder().addAllConditionBoostSpecs(List.of(boostSpec0, boostSpec1)).build();
        try {
            SearchServiceClient searchServiceClient = SearchServiceClient.create();
            Builder pageBuilder = SearchRequest.newBuilder()
                .setPlacement(CommonBuilder.buildPlacement())
                .setBranch(CommonBuilder.buildBranch())
                .setVisitorId("test-a")
                .setBoostSpec(boostSpec)
                .setPageSize(120);

            while (true) {
                ApiFuture<SearchPagedResponse> responseFuture = searchServiceClient.searchPagedCallable().futureCall(pageBuilder.build());
                SearchPagedResponse response = responseFuture.get();
                if (StringUtils.isBlank(response.getNextPageToken())) {
                    break;
                }
                pageBuilder.setPageToken(response.getNextPageToken());
                for (SearchResponse.SearchResult result : response.getPage().getResponse().getResultsList()) {
                    String name = result.getProduct().getName();
                    Triple<String, Boolean, Double> matchPair = SyncGeoHashService.checkGeoHash(name, placeId);
                    System.out.println("brand=[" + matchPair.getLeft() + "]id=[" + name.substring(name.lastIndexOf("/") + 1) + "]isMatch=[" + matchPair.getMiddle() + "]distance=[" + matchPair.getRight() + "]");
                }
            }
        } catch (Exception ex) {
            log.error("doSearchWithPage error={}", ex.getMessage(), ex);
        }
    }
}
