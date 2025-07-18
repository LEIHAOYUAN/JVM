package com.lei.jvm.google.retail;

import com.google.api.core.ApiFuture;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.rpc.UnknownException;
import com.google.cloud.retail.v2.AddLocalInventoriesMetadata;
import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.AddLocalInventoriesResponse;
import com.google.cloud.retail.v2.ImportMetadata;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsRequest.ReconciliationMode;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.ListProductsRequest;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductInlineSource;
import com.google.cloud.retail.v2.ProductInputConfig;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.cloud.retail.v2.ProductServiceClient.ListProductsPage;
import com.google.cloud.retail.v2.ProductServiceClient.ListProductsPagedResponse;
import com.google.cloud.retail.v2.PurgeProductsRequest;
import com.google.cloud.retail.v2.RemoveLocalInventoriesMetadata;
import com.google.cloud.retail.v2.RemoveLocalInventoriesResponse;
import com.google.common.collect.Lists;
import com.google.protobuf.FieldMask;
import com.lei.jvm.google.retail.build.CommonBuilder;
import com.lei.jvm.google.retail.builder.ProductBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 * @see 映射关系 https://wonder.atlassian.net/wiki/spaces/TP/pages/4086465195/Catalog+Data+Mapping
 */
@Slf4j
public class ProductClient {
    private static ExecutorService MONITOR_EXECUTOR = Executors.newFixedThreadPool(10);

    public static void doDelete() throws Exception {
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            productServiceClient.deleteProduct(CommonBuilder.buildProduct(ProductBuilder.PRODUCT_ID));
        }
    }

    private static void clearMemberIds(ListProductsPage page, ProductServiceClient productServiceClient) throws Exception {
        try {
            List<Product> updateProducts = Lists.newArrayList();
            for (Product product : page.getValues()) {
                updateProducts.add(product.toBuilder().clearCollectionMemberIds().build());
            }
            ImportProductsRequest importRequest = ImportProductsRequest.newBuilder()
                .setParent(CommonBuilder.buildBranch())
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
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            Product response = productServiceClient.getProduct(ProductBuilder.buildGetRequest());
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

    public static void doImport() throws Exception {
        ImportProductsRequest request = ProductBuilder.buildImportProductRequest();
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            OperationFuture<ImportProductsResponse, ImportMetadata> future = productServiceClient.importProductsAsync(request);
            /*future.addListener(() -> {
                try {
                    ImportProductsResponse response = future.get();
                    List<Status> errorSamplesList = response.getErrorSamplesList();
                    if (CollectionUtils.isEmpty(errorSamplesList)) {
                        return;
                    }
                    log.info("error_messages={}", JSON.toJSON(errorSamplesList));
                } catch (Exception ex) {
                    if (ex instanceof UnknownException) {
                        return;
                    }
                    log.error("import exception：{}", ex.getMessage(), ex);
                }
            }, MONITOR_EXECUTOR);*/
        }
    }

    public static void doRemoveLocalInventory() throws Exception {
        ProductServiceClient productServiceClient = ProductServiceClient.create();
        OperationFuture<RemoveLocalInventoriesResponse, RemoveLocalInventoriesMetadata> future = productServiceClient.removeLocalInventoriesAsync(ProductBuilder.buildRemoveLocalInventoriesRequest());
        List<String> errorMessages = Lists.newArrayList();
        future.addListener(() -> {
            try {
                future.get(10, TimeUnit.SECONDS);
                log.info("result");
            } catch (Exception ex) {
                if (!(ex.getCause() instanceof UnknownException)) {
                    errorMessages.add(ex.getMessage());
                }
            }
        }, MONITOR_EXECUTOR);
    }

    public static void doAddLocalInventory() throws Exception {
        ProductServiceClient productServiceClient = ProductServiceClient.create();
        AddLocalInventoriesRequest request = ProductBuilder.buildAddLocalInventoriesRequest();
        OperationFuture<AddLocalInventoriesResponse, AddLocalInventoriesMetadata> future = productServiceClient.addLocalInventoriesAsync(request);
        List<String> errorMessages = Lists.newArrayList();
        future.addListener(() -> {
            try {
                AddLocalInventoriesResponse response = future.get();
            } catch (Exception ex) {
                if (!(ex.getCause() instanceof UnknownException)) {
                    errorMessages.add(ex.getMessage());
                }
            }
        }, MONITOR_EXECUTOR);
    }


}
