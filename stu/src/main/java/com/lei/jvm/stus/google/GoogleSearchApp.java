package com.lei.jvm.stus.google;

import cn.hutool.core.date.StopWatch;
import com.alibaba.fastjson.JSONArray;
import com.google.api.gax.rpc.FailedPreconditionException;
import com.google.api.gax.rpc.UnknownException;
import com.google.cloud.retail.v2.Product;
import com.google.common.collect.Lists;
import com.lei.jvm.stus.google.retail.ProductClient;
import com.lei.jvm.stus.google.retail.SearchClient;
import com.lei.jvm.stus.google.retail.build.CommonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ryan
 * @see <a href="https://cloud.google.com/java/docs/reference/google-cloud-retail/latest/com.google.cloud.retail.v2">...</a>
 * GOOGLE_APPLICATION_CREDENTIALS
 * 【搜索】
 * C:\工作文档\BaiduSyncdisk\google\dev-ai-search-user.json
 * D:\工作文档\畅拓科技\BaiduSyncdisk\google\dev-ai-search-user.json
 * 【推荐】
 * C:\工作文档\BaiduSyncdisk\google\dev-search-restaurant-user.json
 * D:\工作文档\畅拓科技\BaiduSyncdisk\google\dev-ai-search-user.json
 */
@Slf4j
public class GoogleSearchApp {
    public static String json = """
        [
          "6b4e3de6-05cc-4564-9e1a-bf0c7852e2f1",
          "2302d05a-fb52-4a7b-9e0f-3f4b5218c9f7",
          "b171b456-2ff4-4f3d-87b6-381a0652903a",
          "6052b283-1617-4bda-ba92-38239808101b",
          "2553e547-97ec-4d19-8088-e42b502ed50a",
          "747ca3ff-eeec-44a5-961c-2d6d37252e46",
          "6cea13d9-e8c5-474a-bf91-30ad5bb453aa",
          "b92d3e1d-4bc5-476f-84ae-34c43b325102",
          "8a5b2437-48eb-41f1-bd6f-5fdd6810ad6a",
          "f83610ce-bcee-4d30-a553-3a667fbc82b7",
          "0e498c27-55e2-41e4-b370-128ac816be65",
          "068a432e-bc40-4a5d-a962-5cbff5b600b8",
          "51a7e9be-ab5d-4ade-a509-6605c9912013",
          "3b5ffd16-eb51-4da9-87a4-26ba101e0f48",
          "73db8cd6-9f13-498a-b512-3b270690cf0b",
          "82b81883-a749-4c8e-bf9f-caf3b622fefb",
          "7b63268d-5e2a-4bc2-b11d-07c0343ef9a7",
          "c045bf23-4061-46d9-a9b6-d6f848ebf789",
          "1a9922e1-ab21-47d8-93a5-27301517067f",
          "8ae4d8aa-3ef9-4924-a822-18875822f3ac",
          "28ca8612-55c3-4ef5-b21b-e8812f0e12b8",
          "9314fa9a-8aaf-45d1-9f44-2a29899a2652",
          "b9be94ad-e4fd-47fb-a507-a949592bf65e",
          "020c0702-8f69-43e5-b6a0-f055b191278c",
          "486d36ca-bd55-4f0d-90b4-bbd2eaabd3fe",
          "6c8d6905-564a-4c2a-b79e-3043d2760c18",
          "bf8dee76-f1de-46be-b584-26d0e37adbf3",
          "17429533-c069-4bbb-b145-dd5c57515ff6",
          "47f43ec3-251f-453f-93ca-5a05519c9080",
          "e217d886-c7cf-481e-a736-6f591399642f",
          "b16cfa36-d62c-449c-9c3a-052cf2fe23a7",
          "7a2cfbd7-5e17-427c-8528-11faadecf787",
          "ae5ba051-f8c4-4079-950f-abd3bc5062dd",
          "18cdcacd-f0bb-4ed8-a1b2-ecb834d1c9dc",
          "780f7155-30e1-4803-a01d-dab203a696cc",
          "80d373e1-8eed-4abe-9210-b209342f8289",
          "a50df891-d411-4126-9b79-9e7ac91cee01",
          "98c58d9c-e609-4391-a425-f5e53c27f409",
          "8d39d40e-94d8-4de3-aa65-8ea777732e51",
          "ef5f63ab-c8be-4578-8e22-6b00961d4e2f",
          "a4a4a865-ae31-4c94-ae04-be19deb23c20",
          "a7d5bd53-291b-4298-891b-7bc0038a02ea",
          "9595e83b-7568-4c75-a0bc-bfd1ce66ab9c",
          "77e18b4e-b04b-4e66-9012-b81f7fa67324",
          "cf047024-1eab-416e-a436-0d5a7292e0b1",
          "9ce7a5f2-26b9-44a9-a0f1-2a388bc1bded",
          "8e581fae-92f1-46b9-92f9-eef59f076f32",
          "d0cb9ce5-efb3-4715-8d8b-62ba05bf7a20",
          "6cc342af-52bb-4395-a090-d409d031e432",
          "6334c1f5-9bc9-4a78-bc91-b68826f28f16",
          "6d4fa46e-d853-414d-866f-ee0f102ec5d4",
          "0c79c58e-b023-4132-9c00-278d046bae42",
          "aaf986ef-3840-4cf5-92dc-8c8c9a273bc4",
          "dad590f9-47b8-46bb-9330-f95cc8e3c157",
          "4f14c59f-f7d9-4aee-b8cd-59405c17fb93",
          "23c5b364-acc8-4628-93cf-42d6d3f05d1b",
          "7933446e-e3c0-486b-a04e-2478be99669d",
          "f6650ebb-d2b8-48b1-b9d4-6a2ed0f805c6",
          "6f56c27a-d0fa-4378-8bd2-2c4848fc8abd",
          "caf7b638-9971-4128-97d3-70c5e6c89723",
          "cf03aaaf-5409-4cd6-94c2-4e66ea52bc44",
          "548805c1-a41e-4cc3-bba1-65b4156e7f8e",
          "6ce8c3e8-39f5-4a17-9014-d092d0ed5a60",
          "e7a8f3dd-3acd-4d97-bac5-c3d3afa3c6ae",
          "aad64372-5a7f-47ca-a646-5f8fee039599",
          "a77f49a7-bbd2-4064-90ad-46e504926798"
        ]
        """;

    public static String productId = "8ae4d8aa-3ef9-4924-a822-18875822f3ac";
    public static List<String> productIds = JSONArray.parseArray(json,String.class);

    public static void main(String[] args) throws IOException {
//        ProductClient.doGetById(productId);
//        ProductClient.doImportWithOperation(productId);
//        ProductClient.doCreate(productId);
//        ProductClient.doUpdateV2(productId);
        for (String id : productIds) {
            ProductClient.doDelete(id);
        }
//        SyncGeoHashService.doSyncLocalInventory(productId);
//        SyncGeoHashService.doSyncLocalInventoryLimit(productId);

//        SearchClient.doSearchWithPage();
//        testSearchDelay();
//        PurgeClient.doPurge();
        //ProductClient.doCheckOperation("projects/208513401313/locations/global/catalogs/default_catalog/branches/0/operations/add-local-inventories-6886959645317252593");
        try {
            Thread.sleep(8000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNotifyException(Exception ex) {
        if (ex == null) {
            return false;
        }
        if (ex instanceof UnknownException || ex.getCause() instanceof UnknownException) {
            return false;
        }
        if (ex instanceof TimeoutException || ex.getCause() instanceof TimeoutException) {
            return false;
        }
        if (ex instanceof FailedPreconditionException || ex.getCause() instanceof FailedPreconditionException) {
            return false;
        }
        return true;
    }


    public static void testImportDelay() {
        ProductClient.doImportWithMetadata(productId);
        StopWatch stopWatch = StopWatch.create("monitor");
        stopWatch.start();
        while (true) {
            Product product = ProductClient.doGetById(productId);
            if (product != null && product.getTitle().startsWith(CommonBuilder.TITLE_PREFIX)) {
                break;
            }
            LockSupport.parkNanos(1_000_000);
        }
        stopWatch.stop();
        log.info("import生效总耗时=[{}]秒", stopWatch.getTotalTimeSeconds());
    }

    public static void testSearchDelay() {
        StopWatch stopWatch = StopWatch.create("monitor");
        stopWatch.start();
        outerLoop:
        while (true) {
            List<Product> productList = SearchClient.doSearch(productId);
            for (Product product : productList) {
                if (product != null && product.getName().contains(productId)) {
                    break outerLoop;
                }
            }
            LockSupport.parkNanos(1_000_000);
        }
        stopWatch.stop();
        log.info("search生效总耗时=[{}]秒", stopWatch.getTotalTimeSeconds());
    }

}
