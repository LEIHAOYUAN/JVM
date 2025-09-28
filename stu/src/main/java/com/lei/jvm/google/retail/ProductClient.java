package com.lei.jvm.google.retail;

import com.alibaba.fastjson.JSON;
import com.google.api.core.ApiFuture;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.rpc.NotFoundException;
import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.DeleteProductRequest;
import com.google.cloud.retail.v2.GetProductRequest;
import com.google.cloud.retail.v2.ImportMetadata;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.cloud.retail.v2.UpdateProductRequest;
import com.google.protobuf.Empty;
import com.google.rpc.Status;
import com.lei.jvm.google.retail.builder.ProductBuilder;
import com.lei.jvm.google.retail.geohash.ProductConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 * @see 映射关系 https://wonder.atlassian.net/wiki/spaces/TP/pages/4086465195/Catalog+Data+Mapping
 */
@Slf4j
public class ProductClient {

    public static Product doGetByName(String name) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            GetProductRequest request = GetProductRequest.newBuilder().setName(name).build();
            return productServiceClient.getProduct(request);
        } catch (Exception ex) {
            log.error("doGetByName_Exception={}", ex.getMessage(), ex);
            return null;
        }
    }

    public static Product doGetById(String productId) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            GetProductRequest request = ProductBuilder.buildGetRequest(productId);
            return productServiceClient.getProduct(request);
        } catch (Exception ex) {
            log.error("doGetById_Exception={}", ex.getMessage(), ex);
            return null;
        }
    }

    public static void doImport(String productId) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            ImportProductsRequest request = ProductBuilder.buildImportProductRequest(productId);
            OperationFuture<ImportProductsResponse, ImportMetadata> future = productServiceClient.importProductsAsync(request);
            future.addListener(() -> {
                try {
                    ImportMetadata metadata = future.getMetadata().get();
                    if (metadata != null && metadata.getFailureCount() > 0) {
                        ImportProductsResponse importProductsResponse = future.get();
                        List<Status> errorSamplesList = importProductsResponse.getErrorSamplesList();
                        List<String> errorMsgs = errorSamplesList.stream().map(Status::getMessage).distinct().toList();
                        log.error("doImport_errorMsg={}", JSON.toJSON(errorMsgs));
                    }
                    log.info("doImport_end_productId={}", productId);
                } catch (Exception ex) {
                    log.error("doImport_Exception:{}", ex.getMessage(), ex);
                }
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("doImport_error={}", ex.getMessage(), ex);
        }
    }

    public static void doCreate(String productId) {
        try {
            CreateProductRequest request = ProductBuilder.buildCreateRequest(productId);
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            productServiceClient.createProduct(request);
            ApiFuture<Product> future = productServiceClient.createProductCallable().futureCall(request);
            future.addListener(() -> {
                try {
                    Product product = future.get(ProductConstant.DEFAULT_TIMEOUT_MINUTES, ProductConstant.DEFAULT_TIMEOUT_UNIT);
                    log.info("doCreate_product_id:{}", product.getId());
                } catch (Exception ex) {
                    log.error("doCreate_Exception={}", ex.getMessage(), ex);
                }
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("doCreate_error={}", ex.getMessage(), ex);
        }
    }

    public static void doUpdate(String productId) {
        try {
            UpdateProductRequest request = ProductBuilder.buildUpdateRequest(productId);
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            productServiceClient.updateProduct(request);
            ApiFuture<Product> future = productServiceClient.updateProductCallable().futureCall(request);
            future.addListener(() -> {
                try {
                    Product product = future.get(ProductConstant.DEFAULT_TIMEOUT_MINUTES, ProductConstant.DEFAULT_TIMEOUT_UNIT);
                    log.info("doUpdate_product_id:{}", product.getId());
                } catch (Exception ex) {
                    log.error("doUpdate_Exception={}", ex.getMessage(), ex);
                }
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("doUpdate_error={}", ex.getMessage(), ex);
        }
    }

    public static void doDelete(String productId) {
        try {
            DeleteProductRequest request = ProductBuilder.buildDeleteRequest(productId);
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            ApiFuture<Empty> future = productServiceClient.deleteProductCallable().futureCall(request);
            future.addListener(() -> {
                try {
                    Empty empty = future.get(ProductConstant.DEFAULT_TIMEOUT_MINUTES, ProductConstant.DEFAULT_TIMEOUT_UNIT);
                    if (Empty.getDefaultInstance().equals(empty)) {
                        log.info("doDelete_product_id={}", productId);
                    } else {
                        log.error("doDelete_Failed_productId={}", productId);
                    }
                } catch (Exception ex) {
                    if (ex instanceof NotFoundException || ex.getCause() instanceof NotFoundException) {
                        return;
                    }
                    log.error("doDelete_Exception={}", ex.getMessage(), ex);
                }
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("doDelete_error={}", ex.getMessage(), ex);
        }
    }


}
