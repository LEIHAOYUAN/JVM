package com.lei.jvm.google.retail;

import com.alibaba.fastjson.JSON;
import com.google.api.gax.longrunning.OperationFuture;
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
import com.google.rpc.Status;
import com.lei.jvm.google.retail.builder.ProductBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 * @see 映射关系 https://wonder.atlassian.net/wiki/spaces/TP/pages/4086465195/Catalog+Data+Mapping
 */
@Slf4j
public class ProductClient {
    private static ExecutorService MONITOR_EXECUTOR = Executors.newFixedThreadPool(10);

    public static Product doGet(String productId) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            GetProductRequest request = ProductBuilder.buildGetRequest(productId);
            return productServiceClient.getProduct(request);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void doCreate(String productId) {
        try {
            CreateProductRequest request = ProductBuilder.buildCreateRequest(productId);
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            productServiceClient.createProduct(request);
        } catch (Exception ex) {
            log.error("doCreate error={}", ex.getMessage(), ex);
        }
    }

    public static void doUpdate(String productId) {
        try {
            UpdateProductRequest request = ProductBuilder.buildUpdateRequest(productId);
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            productServiceClient.updateProduct(request);
        } catch (Exception ex) {
            log.error("doUpdate error={}", ex.getMessage(), ex);
        }
    }

    public static void doDelete(String productId) {
        try {
            DeleteProductRequest request = ProductBuilder.buildDeleteRequest(productId);
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            productServiceClient.deleteProduct(request);
        } catch (Exception ex) {
            log.error("doDelete error={}", ex.getMessage(), ex);
        }
    }

    public static void doImportWithFuture(String productId) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            ImportProductsRequest request = ProductBuilder.buildImportProductRequest(productId);
            OperationFuture<ImportProductsResponse, ImportMetadata> future = productServiceClient.importProductsAsync(request);
            try {
                ImportMetadata metadata = future.getMetadata().get();
                if (metadata != null && metadata.getFailureCount() > 0) {
                    ImportProductsResponse importProductsResponse = future.get();
                    List<Status> errorSamplesList = importProductsResponse.getErrorSamplesList();
                    List<String> errorMsgs = errorSamplesList.stream().map(Status::getMessage).distinct().toList();
                    log.error("错误详情={}", JSON.toJSON(errorMsgs));
                }
                log.info("import end");
            } catch (Exception ex) {
                log.error("import exception：{}", ex.getMessage(), ex);
            }
        } catch (Exception ex) {
            log.error("导入异常={}", ex.getMessage(), ex);
        }
    }

    public static void doImportWithCall(String productId) {
        try {
            ProductServiceClient productServiceClient = ProductServiceClient.create();
            ImportProductsRequest request = ProductBuilder.buildImportProductRequest(productId);
            Operation call = productServiceClient.importProductsCallable().call(request);
            try {
                if (call.hasResponse()) {
                    ImportProductsResponse unpack = call.getResponse().unpack(ImportProductsResponse.class);
                    List<Status> errorSamplesList = unpack.getErrorSamplesList();
                    if (CollectionUtils.isEmpty(errorSamplesList)) {
                        return;
                    }
                }
                log.info("import end");
            } catch (Exception ex) {
                log.error("import exception：{}", ex.getMessage(), ex);
            }
        } catch (Exception ex) {
            log.error("导入异常={}", ex.getMessage(), ex);
        }
    }


}
