package com.lei.jvm.google.retail.builder;

import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.BigQuerySource;
import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.CustomAttribute;
import com.google.cloud.retail.v2.GcsSource;
import com.google.cloud.retail.v2.ImportErrorsConfig;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.LocalInventory;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.ProductInlineSource;
import com.google.common.collect.Lists;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Timestamp;
import com.lei.jvm.google.retail.build.BranchBuilder;

import java.util.List;

/**
 * @author ryan
 */
public class ProductBuilder {


    public static CreateProductRequest buildCreateProductRequest() {
        return CreateProductRequest.newBuilder()
                .setParent(BranchBuilder.buildBranch())
                .setProduct(ProductBuilder.buildProduct())
                .setProductId("productId-1051830678")
                .build();
    }

    public static ImportProductsRequest buildImportProductRequest() {
        ImportProductsRequest request =
                ImportProductsRequest.newBuilder()
                        .setParent(BranchBuilder.buildBranch())
                        .setInputConfig(buildInputConfig())
                        .setErrorsConfig(buildErrorConfig())
                        .setUpdateMask(FieldMask.newBuilder().build())
                        // 设置为true时，表示如果产品不存在，则创建新产品；如果产品已存在，则更新现有产品。
                        // .setReconciliationMode(ImportProductsRequest.ReconciliationMode.INCREMENTAL)
                        // full模式会先删除再创建
                        // .setReconciliationMode(ImportProductsRequest.ReconciliationMode.FULL)
                        //.setNotificationPubsubTopic("notificationPubsubTopic-1361224991")
                        .build();
        return request;
    }

    public static ImportErrorsConfig buildErrorConfig() {
        return ImportErrorsConfig.newBuilder()
                .setGcsPrefix("gs://your-bucket-name/errors/")
                .build();
    }

    public static com.google.cloud.retail.v2.ProductInputConfig buildInputConfig() {
        // GcsSource
        GcsSource gcsSource = GcsSource.newBuilder().build();
        // BigQuerySource
        BigQuerySource bigQuerySource = BigQuerySource.newBuilder().build();
        // inlineProduct
        ProductInlineSource productInlineSource = ProductInlineSource.newBuilder()
                .addAllProducts(Lists.newArrayList(buildProduct()))
                .build();
        return com.google.cloud.retail.v2.ProductInputConfig.newBuilder()
                .setGcsSource(gcsSource)
                .setBigQuerySource(bigQuerySource)
                .setProductInlineSource(productInlineSource)
                .build();
    }

    public static AddLocalInventoriesRequest buildAddLocalInventoriesRequest() {
        return AddLocalInventoriesRequest.newBuilder()
                .setProduct(BranchBuilder.buildBranch())
                .addAllLocalInventories(buildLocalInventories())
                .setAddMask(FieldMask.newBuilder().build())
                .setAddTime(Timestamp.newBuilder().build())
                .setAllowMissing(true)
                .build();
    }

    private static Product buildProduct() {
        return Product.newBuilder()
                .setTitle("restaurant_brand_name-1051830678")
                .addAllCategories(BranchBuilder.buildCatagoryList())
                .addBrands("custmerBrands")
                .setType(Product.Type.COLLECTION)
                // 同时指定inventory数据
                .addAllLocalInventories(buildLocalInventories())
                .build();
    }

    // TODO 设置格式：{"placeId": "dr5rfd6", "attributes": {"availbility": {"numbers": [1]}}}
    private static List<LocalInventory> buildLocalInventories() {
        LocalInventory localInventory = LocalInventory.newBuilder()
                .setPlaceId("dr7253j")
                .putAttributes("availbility", buildCustomAttribute())
                .build();
        return List.of(localInventory);
    }
    
    private static CustomAttribute buildCustomAttribute() {
        return CustomAttribute.newBuilder()
                .addNumbers(1)
                .build();
    }


}
