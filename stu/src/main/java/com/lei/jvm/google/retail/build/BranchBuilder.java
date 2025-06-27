package com.lei.jvm.google.retail.build;

import com.google.cloud.retail.v2.BranchName;

/**
 * location官网：https://cloud.google.com/vertex-ai/docs/general/locations?hl=zh-cn
 */
public class BranchBuilder {

    public static String buildBranch() {
        return BranchName.of("wonder-ai-search-dev", "global", "default_catalog", "2").toString();
    }

}
