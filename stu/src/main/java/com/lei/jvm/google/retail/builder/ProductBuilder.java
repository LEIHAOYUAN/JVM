package com.lei.jvm.google.retail.builder;

import com.google.cloud.retail.v2.AddLocalInventoriesRequest;
import com.google.cloud.retail.v2.CustomAttribute;
import com.google.cloud.retail.v2.GetProductRequest;
import com.google.cloud.retail.v2.ImportErrorsConfig;
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
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author ryan
 */
public class ProductBuilder {

    public static final String PRODUCT_ID = "restaurant_brand_name-10000000101";

    public static GetProductRequest buildGetRequest() {
        return GetProductRequest.newBuilder().setName(CommonBuilder.buildSearchProduct(PRODUCT_ID)).build();
    }

    public static ImportProductsRequest buildImportProductRequest() {
        String product1 = "fdafdsafdsafdsafdsafsdafdsafdsafdsafdsafdsafdsafdsafdasfdsafdafdsafdsafdsafdsafsdafdsafdsafdsafdsafdsafdsafdsafdasfdsafdasfdasfdas001";
        String product2 = "fdafdsafdsafdsafdsafsdafdsafdsafdsafdsafdsafdsafdsafdasfdsafdafdsafdsafdsafdsafsdafdsafdsafdsafdsafdsafdsafdsafdasfdsafdasfdasfdas002";
         List<Product> productList = Lists.newArrayList(buildProduct(product1),buildProduct(product2));
        ImportProductsRequest request = ImportProductsRequest.newBuilder()
            .setParent(CommonBuilder.buildSearchBranch())
            .setInputConfig(ProductInputConfig.newBuilder()
                .setProductInlineSource(ProductInlineSource.newBuilder().addAllProducts(productList).build())
                .build())
            // 设置为true时，表示如果产品不存在，则创建新产品；如果产品已存在，则更新现有产品。
            .setReconciliationMode(ImportProductsRequest.ReconciliationMode.INCREMENTAL)
            .setUpdateMask(FieldMask.newBuilder().build())
            // .setErrorsConfig(ImportErrorsConfig.newBuilder().build())
            // 如果指定更新字段，则product不存在不会创建
            // .setUpdateMask(FieldMask.newBuilder().addPaths("title").build())
            // full模式会先删除再创建
            // .setReconciliationMode(ImportProductsRequest.ReconciliationMode.FULL)
            //.setNotificationPubsubTopic("notificationPubsubTopic-1361224991")
            .build();
        return request;
    }

    private static Product buildProduct(String productId) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }
        List<String> collectionMemberIds = Lists.newArrayList();
        return Product.newBuilder().setId(productId)
            .setTitle(productId)
            .addAllCollectionMemberIds(collectionMemberIds)
            .addAllCategories(CommonBuilder.buildCatagoryList())
            .addBrands("custmerBrands").setType(Type.COLLECTION)
            .addAllLocalInventories(buildLocalInventories())
            //.setDescription("test12345789")
            .build();
    }

    private static Product buildProductItems() {
        return Product.newBuilder().setId(PRODUCT_ID + "-1")
            .setTitle(PRODUCT_ID + "-1")
            .addAllCategories(CommonBuilder.buildCatagoryList())
            .addBrands("custmerBrands").setType(Type.PRIMARY)
            .addAllLocalInventories(buildLocalInventories())
            //.setDescription("test12345789")
            .build();
    }

    public static RemoveLocalInventoriesRequest buildRemoveLocalInventoriesRequest() {
        return RemoveLocalInventoriesRequest.newBuilder()
            .setProduct(CommonBuilder.buildRecProduct(PRODUCT_ID))
            .addAllPlaceIds(Lists.newArrayList("dr7253j", "dr72530"))
            .setRemoveTime(CommonBuilder.buildUTCTimestamp())
            .build();
    }

    public static AddLocalInventoriesRequest buildAddLocalInventoriesRequest() {
        return AddLocalInventoriesRequest.newBuilder()
            .setProduct(CommonBuilder.buildRecProduct(PRODUCT_ID))
            .addAllLocalInventories(buildLocalInventories())
            .setAddTime(CommonBuilder.buildUTCTimestamp())
            .build();
    }

    // TODO 设置格式：{"placeId": "dr5rfd6", "attributes": {"availbility": {"numbers": [1]}}}
    private static List<LocalInventory> buildLocalInventories() {
        LocalInventory localInventory = LocalInventory.newBuilder().setPlaceId("dr72530").putAttributes("availbility", buildCustomAttribute()).build();
        return Lists.newArrayList(localInventory);
    }

    private static CustomAttribute buildCustomAttribute() {
        return CustomAttribute.newBuilder().addNumbers(2).build();
    }


}
