package com.lei.jvm.google.retail;

import cn.hutool.core.collection.CollectionUtil;
import com.google.api.core.ApiFuture;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.AddLocalInventoriesResponse;
import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.GetProductRequest;
import com.google.cloud.retail.v2.ImportMetadata;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsRequest.ReconciliationMode;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.ListProductsRequest;
import com.google.cloud.retail.v2.ListProductsRequest.Builder;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductInlineSource;
import com.google.cloud.retail.v2.ProductInputConfig;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.cloud.retail.v2.ProductServiceClient.ListProductsPage;
import com.google.cloud.retail.v2.ProductServiceClient.ListProductsPagedResponse;
import com.google.cloud.retail.v2.PurgeProductsRequest;
import com.google.common.collect.Lists;
import com.google.protobuf.FieldMask;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.builder.ProductBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 * @see 映射关系 https://wonder.atlassian.net/wiki/spaces/TP/pages/4086465195/Catalog+Data+Mapping
 */
@Slf4j
public class ProductClient {
    public static void doClearAll() throws Exception {
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            GetProductRequest getRequest = GetProductRequest.newBuilder()
                    .setName(CommonBuilder.buildRecProduct("3c5b74b3-69ac-49f6-b698-e38b897c15c8"))
                    .build();
            Product product = productServiceClient.getProduct(getRequest);
            ImportProductsRequest importRequest = ImportProductsRequest.newBuilder()
                    .setParent(CommonBuilder.buildRecBranch())
                    .setInputConfig(ProductInputConfig.newBuilder().setProductInlineSource(ProductInlineSource.newBuilder().addAllProducts(Lists.newArrayList(product)).build()).build())
                    .setUpdateMask(FieldMask.newBuilder().build())
                    .setReconciliationMode(ReconciliationMode.FULL)
                    .build();
            OperationFuture<ImportProductsResponse, ImportMetadata> future = productServiceClient.importProductsAsync(importRequest);
        }
    }

    public static void doClear() throws Exception {
        Builder builder = ListProductsRequest.newBuilder()
                .setParent(CommonBuilder.buildRecBranch())
                .setPageSize(100)
                .setPageToken("");
        ProductServiceClient productServiceClient = ProductServiceClient.create();
        while (true) {
            ListProductsPagedResponse response = doListWithPage(productServiceClient, builder.build());
            ListProductsPage page = response.getPage();
            if (CollectionUtil.isEmpty(page.getValues())) {
                log.info("处理完成");
                break;
            }
            log.info("查询下一页产品={}", response.getNextPageToken());
            builder.setPageToken(response.getNextPageToken());
            clearMemberIds(page, productServiceClient);
            // Thread.sleep(10000);
            doDelete(page, productServiceClient);
        }
    }

    private static void doDelete(ListProductsPage page, ProductServiceClient productServiceClient) {
        for (Product product : page.getValues()) {
            // delete
            try {
                productServiceClient.deleteProduct(CommonBuilder.buildRecProduct(product.getId()));
                // ThreadPoolUtil.execute(() -> {});
            } catch (Exception ex) {
                log.error("删除异常productId=[{}]异常={}", product.getId(), ex.getMessage(), ex);
            }
        }
    }

    private static void clearMemberIds(ListProductsPage page, ProductServiceClient productServiceClient) throws Exception {
        try {
            List<Product> updateProducts = Lists.newArrayList();
            for (Product product : page.getValues()) {
                updateProducts.add(product.toBuilder().clearCollectionMemberIds().build());
            }
            ImportProductsRequest importRequest = ImportProductsRequest.newBuilder()
                    .setParent(CommonBuilder.buildRecBranch())
                    .setInputConfig(ProductInputConfig.newBuilder().setProductInlineSource(ProductInlineSource.newBuilder().addAllProducts(updateProducts).build()).build())
                    //.setErrorsConfig(ImportErrorsConfig.newBuilder().build())
                    .setUpdateMask(FieldMask.newBuilder().addPaths("collection_member_ids").build())
                    .setReconciliationMode(ReconciliationMode.INCREMENTAL)
                    .build();
            OperationFuture<ImportProductsResponse, ImportMetadata> future = productServiceClient.importProductsAsync(importRequest);
        } catch (Exception ex) {
            log.error("清除collection_member_ids异常={}", ex.getMessage(), ex);
        }
    }

    private static ListProductsPagedResponse doListWithPage(ProductServiceClient productServiceClient, ListProductsRequest request) throws Exception {
        ApiFuture<ListProductsPagedResponse> responseApiFuture = productServiceClient.listProductsPagedCallable().futureCall(request);
        return responseApiFuture.get();
    }

    public static void doGet() throws Exception {
        GetProductRequest request = GetProductRequest.newBuilder()
                .setName(CommonBuilder.buildRecProduct("3c5b74b3-69ac-49f6-b698-e38b897c15c8"))
                .build();
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
