package com.lei.jvm.google.retail;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.cloud.retail.v2.PurgeProductsMetadata;
import com.google.cloud.retail.v2.PurgeProductsRequest;
import com.google.cloud.retail.v2.PurgeProductsResponse;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.geohash.ProductConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan
 */
@Slf4j
public class PurgeClient {
    public static void doPurge() {
        try {
            PurgeProductsRequest request = PurgeProductsRequest.newBuilder()
                .setParent(CommonBuilder.buildBranch())
                .setForce(true)
                .build();
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            OperationFuture<PurgeProductsResponse, PurgeProductsMetadata> future = productServiceClient.purgeProductsAsync(request);
            future.addListener(() -> {
                try {
                    PurgeProductsResponse purgeProductsResponse = future.get();
                    log.error("purge_count={}", purgeProductsResponse.getPurgeCount());
                } catch (Exception ex) {
                    log.error("purge_exception:{}", ex.getMessage(), ex);
                }
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("purge异常={}", ex.getMessage(), ex);
        }
    }
}
