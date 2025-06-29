package com.lei.jvm.google.retail;

import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.AddLocalInventoriesResponse;
import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.lei.jvm.google.retail.builder.ProductBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 * @see 映射关系 https://wonder.atlassian.net/wiki/spaces/TP/pages/4086465195/Catalog+Data+Mapping
 */
@Slf4j
public class ProductClient {

    public static void doCreate() throws Exception {
        CreateProductRequest request = ProductBuilder.buildCreateProductRequest();
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            productServiceClient.createProduct(request);
        }
    }

    public static void doImport() throws Exception {
        ImportProductsRequest request = ProductBuilder.buildImportProductRequest();
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            ImportProductsResponse response = productServiceClient.importProductsAsync(request).get();
        }
    }

    public static void doAddLocalInventory() throws Exception {
        AddLocalInventoriesRequest request = ProductBuilder.buildAddLocalInventoriesRequest();
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            AddLocalInventoriesResponse response = productServiceClient.addLocalInventoriesAsync(request).get();
        }
    }


}
