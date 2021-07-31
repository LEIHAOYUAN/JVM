package com.excel.price.run;

import com.alibaba.fastjson.JSON;
import com.excel.price.ao.base.SpecInfoModel;
import com.excel.price.ao.base.StockCellModel;
import com.excel.price.ao.base.StockDetailModel;
import com.excel.price.service.base.SpecInfoService;
import com.excel.price.service.base.StockCellService;
import com.excel.price.service.base.StockDetailService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2021/7/30 21:17
 * @Version 1.0
 * @Description
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        Map<String, StockCellModel> cellMap = readStockCellExcel();
        Map<String, StockDetailModel> stockDetailMap = readStockDetailExcel();
        Map<String, String> specPriceMap = buildSpecPriceMap(stockDetailMap);

    }


    private static void paddingDataHandler() {


    }


    private static Map<String, StockDetailModel> readStockDetailExcel() {
        return new StockDetailService().getMap();
    }

    private static Map<String, StockCellModel> readStockCellExcel() {
        return new StockCellService().getMap();
    }

    private static Map<String, SpecInfoModel> readSpecInfoExcel() {
        return new SpecInfoService().getMap();
    }

    private static Map<String, String> buildSpecPriceMap(Map<String, StockDetailModel> stockDetailMap) {
        Map<String, String> specPriceMap = Maps.newHashMap();
        if (MapUtils.isNotEmpty(stockDetailMap)) {
            StockDetailModel detailModel;
            for (Map.Entry<String, StockDetailModel> entry : stockDetailMap.entrySet()) {
                detailModel = entry.getValue();
                if (null == detailModel) {
                    continue;
                }
                if (specPriceMap.containsKey(detailModel.getSpecCode())) {
                    BigDecimal oldPrice = new BigDecimal(specPriceMap.get(detailModel.getSpecCode()));
                    BigDecimal newPrice = new BigDecimal(detailModel.getStockPrice());
                    if (oldPrice.compareTo(newPrice) > 0) {
                        continue;
                    }
                }
                specPriceMap.put(detailModel.getSpecCode(), detailModel.getStockPrice());
            }
            log.info("获取已存在结存单价....{}", JSON.toJSONString(specPriceMap));
        }
        return specPriceMap;
    }


}
