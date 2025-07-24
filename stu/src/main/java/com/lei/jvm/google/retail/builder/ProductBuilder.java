package com.lei.jvm.google.retail.builder;

import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.GetProductRequest;
import com.google.cloud.retail.v2.ImportProductsRequest;
import com.google.cloud.retail.v2.Product;
import com.google.cloud.retail.v2.Product.Type;
import com.google.cloud.retail.v2.ProductInlineSource;
import com.google.cloud.retail.v2.ProductInputConfig;
import com.google.cloud.retail.v2.UpdateProductRequest;
import com.google.common.collect.Lists;
import com.google.protobuf.FieldMask;
import com.lei.jvm.google.retail.build.CommonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author ryan
 */
public class ProductBuilder {

    public static final String PRODUCT_ID = "test-local-inventory-001";

    public static GetProductRequest buildGetRequest(String productId) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }
        return GetProductRequest.newBuilder().setName(CommonBuilder.buildProduct(productId)).build();
    }

    public static CreateProductRequest buildCreateRequest(String productId) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }
        return CreateProductRequest.newBuilder()
            .setParent(CommonBuilder.buildBranch())
            .setProductId(productId)
            .setProduct(buildProduct(productId))
            .build();
    }

    public static UpdateProductRequest buildUpdateRequest(String productId) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }
        return UpdateProductRequest.newBuilder()
            .setProduct(buildProduct(productId))
            .build();
    }

    public static ImportProductsRequest buildImportProductRequest(String productId) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }
        ImportProductsRequest request = ImportProductsRequest.newBuilder()
            .setParent(CommonBuilder.buildBranch())
            .setInputConfig(ProductInputConfig.newBuilder()
                .setProductInlineSource(ProductInlineSource.newBuilder().addAllProducts(Lists.newArrayList(buildProduct(productId))).build())
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

    private static List<Product> buildIllegalProduct() {
        String product1 = "fdafdsafdsafdsafdsafsdafdsafdsafdsafdsafdsafdsafdsafdasfdsafdafdsafdsafdsafdsafsdafdsafdsafdsafdsafdsafdsafdsafdasfdsafdasfdasfdas001";
        String product2 = "fdafdsafdsafdsafdsafsdafdsafdsafdsafdsafdsafdsafdsafdasfdsafdafdsafdsafdsafdsafsdafdsafdsafdsafdsafdsafdsafdsafdasfdsafdasfdasfdas002";
        List<Product> productList = Lists.newArrayList(buildProduct(product1), buildProduct(product2));
        return productList;
    }

    private static List<Product> buildMoreProduct() {
        List<Product> productList = Lists.newArrayList();
        for (int i = 0; i < 1; i++) {
            productList.add(buildProduct("test-local-import-product-" + i));
        }
        return productList;
    }

    private static Product buildProduct(String productId) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }

        List<String> collectionMemberIds = Lists.newArrayList();
        return Product.newBuilder().setId(productId)
            .setTitle(productId)
            //.addAllCollectionMemberIds(collectionMemberIds)
            .addAllCategories(CommonBuilder.buildCatagoryList())
            .addBrands("custmerBrands").setType(Type.COLLECTION)
            //.addAllLocalInventories(buildLocalInventories())
            //.putAttributes("test", CustomAttribute.newBuilder().addAllText(Lists.newArrayList()).build())
            .setDescription("test000000000000")
            .build();
    }

    private static Product buildProductItems() {
        return Product.newBuilder().setId(PRODUCT_ID + "-1")
            .setTitle(PRODUCT_ID + "-1")
            .addAllCategories(CommonBuilder.buildCatagoryList())
            .addBrands("custmerBrands").setType(Type.PRIMARY)
            // .addAllLocalInventories(buildLocalInventories())
            //.setDescription("test12345789")
            .build();
    }

}
