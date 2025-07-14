package com.lei.jvm.google.retail.builder;

import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.CustomAttribute;
import com.google.cloud.retail.v2.GetProductRequest;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.LocalInventory;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.Product.Type;
import com.google.cloud.retail.v2.ProductInlineSource;
import com.google.cloud.retail.v2.ProductInputConfig;
import com.google.cloud.retail.v2.RemoveLocalInventoriesRequest;
import com.google.common.collect.Lists;
import com.google.protobuf.FieldMask;
import com.lei.jvm.google.retail.build.CommonBuilder;

import java.util.List;

/**
 * @author ryan
 */
public class ProductBuilder {

    public static final String PRODUCT_ID = "restaurant_brand_name-1000000006";

    public static GetProductRequest buildGetRequest() {
        return GetProductRequest.newBuilder().setName(CommonBuilder.buildRecProduct(PRODUCT_ID)).build();
    }

    public static ImportProductsRequest buildImportProductRequest() {
        ImportProductsRequest request = ImportProductsRequest.newBuilder().setParent(CommonBuilder.buildRecBranch()).setInputConfig(ProductInputConfig.newBuilder().setProductInlineSource(ProductInlineSource.newBuilder().addAllProducts(Lists.newArrayList(buildProduct())).build()).build()).setUpdateMask(FieldMask.newBuilder().build())
            // 设置为true时，表示如果产品不存在，则创建新产品；如果产品已存在，则更新现有产品。
            .setReconciliationMode(ImportProductsRequest.ReconciliationMode.INCREMENTAL)
            // full模式会先删除再创建
            // .setReconciliationMode(ImportProductsRequest.ReconciliationMode.FULL)
            //.setNotificationPubsubTopic("notificationPubsubTopic-1361224991")
            .build();
        return request;
    }

    private static Product buildProduct() {
        return Product.newBuilder().setId(PRODUCT_ID).setTitle(PRODUCT_ID).addAllCategories(CommonBuilder.buildCatagoryList()).addBrands("custmerBrands").setType(Type.PRIMARY).addAllLocalInventories(buildLocalInventories()).build();
    }

    public static RemoveLocalInventoriesRequest buildRemoveLocalInventoriesRequest() {
        return RemoveLocalInventoriesRequest.newBuilder().setProduct(CommonBuilder.buildRecProduct(PRODUCT_ID)).build();
    }

    public static AddLocalInventoriesRequest buildAddLocalInventoriesRequest() {
        return AddLocalInventoriesRequest.newBuilder().setProduct(CommonBuilder.buildRecProduct(PRODUCT_ID)).addAllLocalInventories(Lists.newArrayList()).build();
        // return AddLocalInventoriesRequest.newBuilder().setProduct(CommonBuilder.buildRecProduct(PRODUCT_ID)).addAllLocalInventories(buildLocalInventories()).build();
    }

    // TODO 设置格式：{"placeId": "dr5rfd6", "attributes": {"availbility": {"numbers": [1]}}}
    private static List<LocalInventory> buildLocalInventories() {
        LocalInventory localInventory = LocalInventory.newBuilder().setPlaceId("dr7253j").putAttributes("availbility", buildCustomAttribute()).build();
        return Lists.newArrayList(localInventory);
    }

    private static CustomAttribute buildCustomAttribute() {
        return CustomAttribute.newBuilder().addNumbers(1).build();
    }


}
