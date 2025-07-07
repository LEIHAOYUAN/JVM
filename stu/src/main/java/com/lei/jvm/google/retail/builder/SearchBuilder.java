package com.lei.jvm.google.retail.builder;

import com.google.cloud.retail.v2.SearchRequest;
import com.google.cloud.retail.v2.UserInfo;
import com.lei.jvm.google.retail.build.CommonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ryan
 */
public class SearchBuilder {

    /**
     * @See <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.SearchRequest">...</a>
     */
    public static SearchRequest buildDefaultSearchRequest() {
        return SearchRequest.newBuilder()
                .setPlacement(CommonBuilder.buildSearchPlacement())
                .setBranch(CommonBuilder.buildRecBranch())
                .setVisitorId("test-a")
                .setQuery("burger")
                .setFilter(("brands: ANY(\"Alanza\")"))
                .setPageSize(120)
                .build();
    }

    public static SearchRequest buildSearchRequest() {
        return SearchRequest.newBuilder()
                .setPlacement(CommonBuilder.buildSearchPlacement())
                .setBranch(CommonBuilder.buildRecBranch())
                .setVisitorId("test-a")
                //.setUserInfo(UserInfo.newBuilder().build())
                .setPageSize(10)
                .setQuery("burger")
                //.setOffset(1)
                //.addAllFacetSpecs(new ArrayList<SearchRequest.FacetSpec>())
                //.setBoostSpec(SearchRequest.BoostSpec.newBuilder().build())
                //.setQueryExpansionSpec(SearchRequest.QueryExpansionSpec.newBuilder().build())
                //.addAllVariantRollupKeys(new ArrayList<String>())
                //.addAllPageCategories(new ArrayList<String>())
                //.setPersonalizationSpec(SearchRequest.PersonalizationSpec.newBuilder().build())
                //.putAllLabels(new HashMap<String, String>())
                //.setSpellCorrectionSpec(SearchRequest.SpellCorrectionSpec.newBuilder().build())
                //.setConversationalSearchSpec(SearchRequest.ConversationalSearchSpec.newBuilder().build())
                //.setTileNavigationSpec(SearchRequest.TileNavigationSpec.newBuilder().build())
                .build();
    }

    public static SearchRequest buildSearchRequestBackup() {
        return SearchRequest.newBuilder()
                .setPlacement("placement1792938725")
                .setBranch(CommonBuilder.buildRecBranch())
                .setQuery("query107944136")
                .setVisitorId("visitorId1880545833")
                .setUserInfo(UserInfo.newBuilder().build())
                .setPageSize(10)
                .setPageToken("pageToken873572522")
                .setOffset(1)
                .setFilter("filter-1274492040")
                .setCanonicalFilter("canonicalFilter-722283124")
                .setOrderBy("orderBy-1207110587")
                .addAllFacetSpecs(new ArrayList<SearchRequest.FacetSpec>())
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
    }

}
