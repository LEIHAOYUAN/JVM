package com.lei.jvm.google.retail;

import com.google.cloud.retail.v2.CatalogServiceClient;

import java.io.IOException;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.CatalogServiceClient#com_google_cloud_retail_v2_CatalogServiceClient_create__">...</a>
 */
public class CatalogClient {

    public static void doCreate() throws IOException {
        CatalogServiceClient.create();
    }


}
