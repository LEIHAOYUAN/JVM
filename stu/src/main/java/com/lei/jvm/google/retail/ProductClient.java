package com.lei.jvm.google.retail;

import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.AddLocalInventoriesResponse;
import com.google.cloud.retail.v2.BranchName;
import com.google.cloud.retail.v2.ImportErrorsConfig;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.LocalInventory;
import com.google.cloud.retail.v2.ProductInputConfig;
import com.google.cloud.retail.v2.ProductName;
import com.google.cloud.retail.v2.ProductServiceClient;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Timestamp;

import java.util.List;

/**
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2.ProductServiceClient">...</a>
 */
public class ProductClient {

    public static void main(String[] args) {

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
                            .setProduct(ProductName.of("[PROJECT]", "[LOCATION]", "[CATALOG]", "[BRANCH]", "[PRODUCT]").toString())
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
                        .setParent(BranchName.of("[PROJECT]", "[LOCATION]", "[CATALOG]", "[BRANCH]").toString())
                        .setInputConfig(ProductInputConfig.newBuilder().build())
                        .setErrorsConfig(ImportErrorsConfig.newBuilder().build())
                        .setUpdateMask(FieldMask.newBuilder().build())
                        .setNotificationPubsubTopic("notificationPubsubTopic-1361224991")
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
