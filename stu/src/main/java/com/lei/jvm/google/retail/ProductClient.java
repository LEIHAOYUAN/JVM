package com.lei.jvm.google.retail;

import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.AddLocalInventoriesResponse;
import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.ImportErrorsConfig;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.LocalInventory;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductInputConfig;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Timestamp;
import com.lei.jvm.google.retail.build.BranchBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 */
@Slf4j
public class ProductClient {

    public static void doCreate() throws Exception {
        Product product = Product.newBuilder()
                .addBrands("custmerBrands")
                .build();
        CreateProductRequest request = CreateProductRequest.newBuilder()
                .setParent(BranchBuilder.buildBranch())
                .setProduct(product)
                .setProductId("productId-1051830678")
                .build();
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            productServiceClient.createProduct(request);
        }
    }

    public static void doImport() throws Exception {
        ImportProductsRequest request = buildRequest();
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            ImportProductsResponse response = productServiceClient.importProductsAsync(request).get();
        }
    }

    public static void doAddLocalInventory() throws Exception {
        try (ProductServiceClient productServiceClient = ProductServiceClient.create()) {
            AddLocalInventoriesRequest request =
                    AddLocalInventoriesRequest.newBuilder()
                            .setProduct(BranchBuilder.buildBranch())
                            .addAllLocalInventories(buildLocalInventories())
                            .setAddMask(FieldMask.newBuilder().build())
                            .setAddTime(Timestamp.newBuilder().build())
                            .setAllowMissing(true)
                            .build();
            AddLocalInventoriesResponse response = productServiceClient.addLocalInventoriesAsync(request).get();
        }
    }

    private static ImportProductsRequest buildRequest() {
        ImportProductsRequest request =
                ImportProductsRequest.newBuilder()
                        .setParent(BranchBuilder.buildBranch())
                        .setInputConfig(ProductInputConfig.newBuilder().build())
                        .setErrorsConfig(ImportErrorsConfig.newBuilder().build())
                        .setUpdateMask(FieldMask.newBuilder().build())
                        //.setNotificationPubsubTopic("notificationPubsubTopic-1361224991")
                        .build();
        return request;
    }

    private static List<LocalInventory> buildLocalInventories() {
        LocalInventory localInventory = LocalInventory.newBuilder()
                .setPlaceId("dr7253j")
                //.putAttributes("", CustomAttributeOrBuilder.newBuilder().build())
                .build();
        return List.of(localInventory);
    }

}
