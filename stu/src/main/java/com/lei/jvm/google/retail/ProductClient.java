package com.lei.jvm.google.retail;

import cn.hutool.core.collection.CollectionUtil;
import com.google.api.core.ApiFuture;
import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.AddLocalInventoriesResponse;
import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.GetProductRequest;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.ListProductsRequest;
import com.google.cloud.retail.v2.ListProductsRequest.Builder;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.cloud.retail.v2.ProductServiceClient.ListProductsPage;
import com.google.cloud.retail.v2.ProductServiceClient.ListProductsPagedResponse;
import com.google.cloud.retail.v2.PurgeProductsRequest;
import com.google.cloud.retail.v2.UpdateProductRequest;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.builder.ProductBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 * @see 映射关系 https://wonder.atlassian.net/wiki/spaces/TP/pages/4086465195/Catalog+Data+Mapping
 */
@Slf4j
public class ProductClient {

    public static void doDelete() throws Exception {
        Builder builder = ListProductsRequest.newBuilder()
                .setParent(CommonBuilder.buildRecBranch())
                .setPageSize(100)
                .setPageToken("");
        ProductServiceClient productServiceClient = ProductServiceClient.create();
        while (true) {
            ListProductsPagedResponse response = doListWithPage(builder.build());
            ListProductsPage page = response.getPage();
            if (CollectionUtil.isEmpty(page.getValues())) {
                break;
            }
            builder.setPageToken(response.getNextPageToken());
            for (Product product : page.getValues()) {
                try {
                    productServiceClient.deleteProduct(CommonBuilder.buildRecProduct(product.getId()));
                } catch (Exception ex) {
                    log.error("删除异常productId=[{}]异常={}", product.getId(), ex.getMessage(), ex);
                }
            }
        }
    }

    private static ListProductsPagedResponse doListWithPage(ListProductsRequest request) throws Exception {
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            ApiFuture<ListProductsPagedResponse> responseApiFuture = productServiceClient.listProductsPagedCallable().futureCall(request);
            return responseApiFuture.get();
        }
    }

    public static void doGet() throws Exception {
        GetProductRequest request = ProductBuilder.buildGetProductRequest("productId-1051830678");
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            Product response = productServiceClient.getProduct(request);
            log.info("查询结果={}", response.toString());
        }
    }


    public static void doPurge(String parentName, String productId) throws Exception {
        PurgeProductsRequest request = PurgeProductsRequest.newBuilder()
                .setParent(parentName)
                .setForce(true)
                .setFilter("id = \"" + productId + "\"")
                .build();
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
