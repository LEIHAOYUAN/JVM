package com.lei.jvm.google.retail.builder;

import com.google.cloud.retail.v2.CreateProductRequest;
import com.google.cloud.retail.v2.CustomAttribute;
import com.google.cloud.retail.v2.DeleteProductRequest;
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
        Product product = buildPrimaryProduct(productId);
        return CreateProductRequest.newBuilder()
            .setParent(CommonBuilder.buildBranch())
            .setProduct(product)
            .setProductId(product.getId())
            .build();
    }

    public static UpdateProductRequest buildUpdateRequest(String productId) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }
        return UpdateProductRequest.newBuilder()
            .setProduct(buildPrimaryProduct(productId))
            .setUpdateMask(FieldMask.newBuilder().build())
            .build();
    }

    public static DeleteProductRequest buildDeleteRequest(String productId) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }
        return DeleteProductRequest.newBuilder()
            .setName(CommonBuilder.buildProduct(productId))
            .build();
    }

    public static List<ImportProductsRequest> buildMoreImportProductRequest(String productId, int itemCount) {
        List<Product> products = buildMoreProduct(productId, itemCount);
        List<ImportProductsRequest> requests = Lists.newArrayList();
        for (List<Product> batch : Lists.partition(products, 100)) {
            requests.add(doBuildImportRequest(batch));
        }
        return requests;
    }

    private static ImportProductsRequest doBuildImportRequest(List<Product> products) {
        return ImportProductsRequest.newBuilder()
            .setParent(CommonBuilder.buildBranch())
            .setInputConfig(ProductInputConfig.newBuilder()
                .setProductInlineSource(ProductInlineSource.newBuilder().addAllProducts(products).build())
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
    }

    private static List<Product> buildMoreProduct(String productId, int itemCount) {
        List<Product> productList = Lists.newArrayList();
        Product primaryProduct = buildPrimaryProduct(productId);
        productList.add(primaryProduct);
        if (itemCount > 0) {
            for (int i = 0; i < itemCount; i++) {
                productList.add(buildVariantProduct(productId + "-" + i, productId));
            }
        }
        return productList;
    }

    private static Product buildPrimaryProduct(String productId) {
        return doBuildProduct(productId, productId, Type.PRIMARY);
    }

    private static Product buildVariantProduct(String productId, String primaryProductId) {
        return doBuildProduct(productId, primaryProductId, Type.VARIANT);
    }

    private static Product doBuildProduct(String productId, String primaryProductId, Type type) {
        if (StringUtils.isBlank(productId)) {
            productId = PRODUCT_ID;
        }
        return Product.newBuilder().setId(productId)
            .setPrimaryProductId(primaryProductId)
            .setName(CommonBuilder.buildProduct(productId))
            .setTitle(CommonBuilder.TITLE_PREFIX + productId)
            //.addAllCollectionMemberIds(collectionMemberIds)
            .addAllCategories(CommonBuilder.buildCatagoryList())
            .addBrands("custmerBrands")
            .setType(type)
            //.addAllLocalInventories(buildLocalInventories())
            .putAttributes("test", CustomAttribute.newBuilder().addAllText(Lists.newArrayList("66666666")).build())
            .setAvailability(Product.Availability.IN_STOCK)
            .setDescription("test000000000000")
            .build();
    }

}
