package com.lei.jvm.google.retail.build;

import com.google.cloud.retail.v2.BranchName;

public class BranchBuilder {

    public static String buildBranch() {
        return BranchName.of("wonder-ai-search-dev", "21438146677", "CATALOG", "Branch 2").toString();
    }

}
