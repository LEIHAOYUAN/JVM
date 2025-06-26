package com.lei.jvm.google;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lei
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        String projectId = "your-google-cloud-project-id";
        authenticateImplicitWithAdc(projectId);
    }

    /**
     * https://cloud.google.com/docs/authentication/client-libraries?hl=zh-cn
     */
    public static void authenticateImplicitWithAdc(String project) {
        Storage storage = StorageOptions.newBuilder().setProjectId(project).build().getService();
        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }
    }

}
