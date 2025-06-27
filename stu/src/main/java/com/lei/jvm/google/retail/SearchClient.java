package com.lei.jvm.google.retail;

import com.google.api.client.util.Strings;
import com.google.api.core.ApiFuture;
import com.google.cloud.retail.v2.BranchName;
import com.google.cloud.retail.v2.SearchRequest;
import com.google.cloud.retail.v2.SearchRequest.FacetSpec;
import com.google.cloud.retail.v2.SearchResponse;
import com.google.cloud.retail.v2.SearchResponse.SearchResult;
import com.google.cloud.retail.v2.SearchServiceClient;
import com.google.cloud.retail.v2.SearchServiceClient.SearchPagedResponse;
import com.google.cloud.retail.v2.UserInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.SearchServiceClient">...</a>
 */
@Slf4j
public class SearchClient {

    public static void doSearch() throws Exception {
        SearchRequest request = buildRequest();
        try (SearchServiceClient searchServiceClient = SearchServiceClient.create()) {
            SearchPagedResponse response = searchServiceClient.search(request);
            for (SearchResult searchResult : response.iterateAll()) {
                // doThingsWith(searchResult);
            }
        }
    }

    public static void doSearchWithCallable() throws Exception {
        SearchRequest request = buildRequest();
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

    public static void doSearchWithPage() throws Exception {
        SearchRequest request = buildRequest();
        try (SearchServiceClient searchServiceClient = SearchServiceClient.create()) {
            ApiFuture<SearchPagedResponse> responseApiFuture = searchServiceClient.searchPagedCallable().futureCall(request);
            SearchPagedResponse searchPagedResponse = responseApiFuture.get();
        }
    }

    private static SearchRequest buildRequest() {
        SearchRequest request = SearchRequest.newBuilder()
                .setPlacement("placement1792938725")
                .setBranch(BranchName.of("[branch0]", "[LOCATION]", "[CATALOG]", "[BRANCH]").toString())
                .setQuery("query107944136")
                .setVisitorId("visitorId1880545833")
                .setUserInfo(UserInfo.newBuilder().build())
                .setPageSize(883849137)
                .setPageToken("pageToken873572522")
                .setOffset(-1019779949)
                .setFilter("filter-1274492040")
                .setCanonicalFilter("canonicalFilter-722283124")
                .setOrderBy("orderBy-1207110587")
                .addAllFacetSpecs(new ArrayList<FacetSpec>())
                .setBoostSpec(SearchRequest.BoostSpec.newBuilder().build())
                .setQueryExpansionSpec(SearchRequest.QueryExpansionSpec.newBuilder().build())
                .addAllVariantRollupKeys(new ArrayList<String>())
                .addAllPageCategories(new ArrayList<String>())
                .setPersonalizationSpec(SearchRequest.PersonalizationSpec.newBuilder().build())
                .putAllLabels(new HashMap<String, String>())
                .setSpellCorrectionSpec(SearchRequest.SpellCorrectionSpec.newBuilder().build())
                .setEntity("entity-1298275357")
                .setConversationalSearchSpec(SearchRequest.ConversationalSearchSpec.newBuilder().build())
                .setTileNavigationSpec(SearchRequest.TileNavigationSpec.newBuilder().build())
                .setLanguageCode("languageCode-2092349083")
                .setRegionCode("regionCode-1991004415")
                .setPlaceId("placeId-494224254")
                .build();
        return request;
    }

}
