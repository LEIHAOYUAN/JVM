package com.lei.jvm.google.retail.config;

/**
 * @author ryan
 */
public final class VAISConfig {
    private static final VAISConfig instance = new VAISConfig();
    private final SearchConfig searchConfig = new SearchConfig();
    private final RecommendConfig recommendConfig = new RecommendConfig();

    private VAISConfig() {
    }

    public static VAISConfig getInstance() {
        return instance;
    }

    public SearchConfig getSearchConfig() {
        return searchConfig;
    }

    public RecommendConfig getRecommendConfig() {
        return recommendConfig;
    }

    public final class SearchConfig {
        public String branch;
        public String catalog;
        public String location;
        public String projectId;

        private SearchConfig() {
        }
    }

    public final class RecommendConfig {
        public String branch;
        public String catalog;
        public String location;
        public String projectId;

        private RecommendConfig() {
        }
    }
}
