package com.lei.jvm.google.retail;

import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.AddLocalInventoriesResponse;
import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.GetProductRequest;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.ListProductsRequest;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.cloud.retail.v2.ProductServiceClient.ListProductsPagedResponse;
import com.google.cloud.retail.v2.PurgeProductsRequest;
import com.google.common.collect.Lists;
import com.lei.jvm.google.retail.builder.ProductBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 * @see 映射关系 https://wonder.atlassian.net/wiki/spaces/TP/pages/4086465195/Catalog+Data+Mapping
 */
@Slf4j
public class ProductClient {

    public static void doList() throws Exception {
        ListProductsRequest request = ProductBuilder.buildListProductsRequest(Lists.newArrayList("productId-1051830678", "a02f481e-b3e7-41a8-b3bd-dcb5e54e35a9"));
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            ListProductsPagedResponse response = productServiceClient.listProducts(request);
            log.info("查询结果={}", response.getPage().getResponse().getProductsCount());
        }
    }

    public static void doGet() throws Exception {
        GetProductRequest request = ProductBuilder.buildGetProductRequest("productId-1051830678");
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            Product response = productServiceClient.getProduct(request);
            log.info("查询结果={}", response.toString());
        }
    }

    public static void doPurge() throws Exception {
        PurgeProductsRequest request = ProductBuilder.buildPurgeProductRequest(Lists.newArrayList("productId-1051830678", "1111111111"));
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            productServiceClient.purgeProductsAsync(request);
        }
    }

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
