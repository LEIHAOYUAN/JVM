package com.lei.jvm.google.retail.build;

import com.google.cloud.retail.v2.BranchName;

public class BranchBuilder {

    public static String buildBranch() {
        return BranchName.of("wonder-ai-search-dev", "us-central1", "default", "default").toString();
    }


}
