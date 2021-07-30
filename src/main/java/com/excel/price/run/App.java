package com.excel.price.run;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.excel.price.ao.StockCellModel;
import com.excel.price.ao.StockDetailModel;
import com.excel.price.service.StockCellService;
import com.excel.price.service.StockDetailService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/7/30 21:17
 * @Version 1.0
 * @Description
 */
@Slf4j
public class App {

    private static final String BASE_PATH = "D:\\工作文档\\预置单价\\";

    public static void main(String[] args) {
        Map<String, StockCellModel> cellMap = readStockCellExcel();
        Map<String, StockDetailModel> stockDetailMap = readStockDetailExcel();
        Map<String, String> specPriceMap = buildSpecPriceMap(stockDetailMap);

    }


    private static void paddingDataHandler() {



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


    /*
     * 规格结存单价信息（相同规格保留最高单价）
     * @return
     */
    private static Map<String, StockDetailModel> readStockDetailExcel() {
        StockDetailService stockDetailService = new StockDetailService();
        EasyExcel.read(BASE_PATH + "单价信息.xlsx", StockDetailModel.class, stockDetailService).sheet().doRead();
        List<StockDetailModel> data = stockDetailService.getData();
        log.info(JSON.toJSONString(data));
        if (CollectionUtils.isEmpty(data)) {
            log.warn("库存单位信息为空");
            return Maps.newHashMap();
        }
        return data.stream().collect(Collectors.toMap(StockDetailModel::getCellName, i -> i, (t, t2) -> t2));
    }

    /**
     * 库存单位信息
     * 第一列：库存单位名称
     * 第二列：库存单位主数据编号
     *
     * @return
     */
    private static Map<String, StockCellModel> readStockCellExcel() {
        StockCellService stockCellService = new StockCellService();
        EasyExcel.read(BASE_PATH + "仓库信息.xlsx", StockCellModel.class, stockCellService).sheet().doRead();
        List<StockCellModel> data = stockCellService.getData();
        log.info(JSON.toJSONString(data));
        if (CollectionUtils.isEmpty(data)) {
            log.warn("库存单位信息为空");
            return Maps.newHashMap();
        }
        return data.stream().collect(Collectors.toMap(StockCellModel::getName, i -> i, (t, t2) -> t2));
    }

}
