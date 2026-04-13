package com.lei.jvm.stus.google.retail;

import com.alibaba.fastjson.JSON;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.NotFoundException;
import com.google.api.gax.rpc.StatusCode;
import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.DeleteProductRequest;
import com.google.cloud.retail.v2.GetProductRequest;
import com.google.cloud.retail.v2.ImportMetadata;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.cloud.retail.v2.UpdateProductRequest;
import com.google.longrunning.Operation;
import com.google.protobuf.Empty;
import com.google.rpc.Status;
import com.lei.jvm.stus.google.retail.builder.ProductBuilder;
import com.lei.jvm.stus.google.retail.geohash.ProductConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
            Product product = productServiceClient.getProduct(request);
            return product;
        } catch (Exception ex) {
            return null;
        }
    }

    public static Product doGetById(String productId) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            GetProductRequest request = ProductBuilder.buildGetRequest(productId);
            Product product = productServiceClient.getProduct(request);
            return product;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void doImportWithOperation(String productId) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            ImportProductsRequest request = ProductBuilder.buildImportProductRequest(productId);
            OperationFuture<ImportProductsResponse, ImportMetadata> future = productServiceClient.importProductsAsync(request);
            future.addListener(() -> {
                try {
                    Operation operation = productServiceClient.getOperationsClient().getOperation(future.getName());

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

    public static void doImportWithMetadata(String productId) {
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

    public static void doCheckOperation(String operationName) {
        try {
            Operation operation = ProductServiceClient.create().getOperationsClient().getOperation(operationName);
            if (operation == null) {
                log.error("operation is null");
                return;
            }
            if (operation.hasError() && StringUtils.isNotBlank(operation.getError().getMessage())) {
                log.error("operation has error, message={}", operation.getError().getMessage());
                return;
            }
            if (operation.getDone()) {
                log.info("operation is done");
            }
        } catch (Exception ex) {
            log.error("doCheckOperation_Exception={}", ex.getMessage(), ex);
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
            ApiFutures.addCallback(future, new ApiFutureCallback<>() {
                @Override
                public void onFailure(Throwable t) {
                    log.error("update-failure={}", t.getMessage());
                }

                @Override
                public void onSuccess(Product product) {
                    log.info("update-success");
                }
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("update-with-callback-exception={}", ex.getMessage(), ex);
        }

    }

    public static void doDelete(String productId) {
        try {
            DeleteProductRequest request = ProductBuilder.buildDeleteRequest(productId);
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            ApiFuture<Empty> future = productServiceClient.deleteProductCallable().futureCall(request);
            ApiFutures.addCallback(future, new ApiFutureCallback<>() {
                @Override
                public void onFailure(Throwable t) {
                    if (!checkIgnoreException(t)) {
                        log.error("delete-failure={}", t.getMessage());
                    }
                }

                @Override
                public void onSuccess(Empty empty) {
                    log.info("delete-success}");
                }
            }, ProductConstant.MONITOR_EXECUTOR);
        } catch (Exception ex) {
            log.error("doDelete_error={}", ex.getMessage(), ex);
        }
    }

    private static boolean checkIgnoreException(Throwable t) {
        if (t == null) return false;
        if (t instanceof NotFoundException) return true;
        if (t instanceof ApiException apiException && apiException.getStatusCode().getCode() == StatusCode.Code.NOT_FOUND) {
            return true;
        }
        if (!t.equals(t.getCause())) {
            return checkIgnoreException(t.getCause());
        }
        return false;
    }


}
