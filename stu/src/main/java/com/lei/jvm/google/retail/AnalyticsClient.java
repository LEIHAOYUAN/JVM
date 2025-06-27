package com.lei.jvm.google.retail;

import com.google.cloud.retail.v2.AnalyticsServiceClient;
import com.google.cloud.retail.v2.ExportAnalyticsMetricsRequest;
import com.google.cloud.retail.v2.ExportAnalyticsMetricsResponse;

public class AnalyticsClient {

    public static void doExport() throws Exception {
        try (AnalyticsServiceClient analyticsServiceClient = AnalyticsServiceClient.create()) {
            ExportAnalyticsMetricsRequest request =
                    ExportAnalyticsMetricsRequest.newBuilder()
                            //.setCatalog("catalog555704345")
                            //.setOutputConfig(OutputConfig.newBuilder().build())
                            //.setFilter("filter-1274492040")
                            .build();
            ExportAnalyticsMetricsResponse response = analyticsServiceClient.exportAnalyticsMetricsAsync(request).get();
        }
    }


}
