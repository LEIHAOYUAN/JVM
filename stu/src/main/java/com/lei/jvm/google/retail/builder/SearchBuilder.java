package com.lei.jvm.google.retail.builder;

import com.google.cloud.retail.v2.SearchRequest;
import com.google.cloud.retail.v2.UserInfo;
import com.lei.jvm.google.retail.build.BranchBuilder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ryan
 */
public class SearchBuilder {
    public static SearchRequest buildSearchRequest() {
        return SearchRequest.newBuilder()
                .setPlacement("placement1792938725")
                .setBranch(BranchBuilder.buildBranch())
                .setQuery("query107944136")
                .setVisitorId("visitorId1880545833")
                .setUserInfo(UserInfo.newBuilder().build())
                .setPageSize(883849137)
                .setPageToken("pageToken873572522")
                .setOffset(-1019779949)
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
