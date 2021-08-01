package com.excel.price.run;

import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.excel.price.ao.RequestDataAO;
import com.excel.price.ao.base.SpecInfoModel;
import com.excel.price.ao.base.StockCellModel;
import com.excel.price.ao.base.StockDetailModel;
import com.excel.price.bo.PriceResultBO;
import com.excel.price.constant.FileConstant;
import com.excel.price.service.RequestDataService;
import com.excel.price.service.base.SpecInfoService;
import com.excel.price.service.base.StockCellService;
import com.excel.price.service.base.StockDetailService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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

        // TODO 解析业务excel，按照仓库维度解析

        // TODO 根据StockDetail数据过滤已经存在单价的明细

        // TODO 对不存在的数据生成Excel，SQL文件


    }


    private void paddingDataHandler() {
        RequestDataService requestDataService = new RequestDataService();
        EasyExcel.read(FileConstant.BASE_PATH_REQUEST_DATA + "网购规格单价处理模板.xlsx", RequestDataAO.class, requestDataService).sheet().doRead();
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


    private void resultHandler(List<PriceResultBO> data) {
        ExcelUtil.getWriter(FileConstant.BASE_PATH_PRICE_RESULT + "待预置单价.xlsx").write(data);
    }

    private void sqlFileHandler(List<PriceResultBO> data) {

        StringBuilder builder = new StringBuilder("INSERT INTO `scm_stock`.`t_stock_detail` (`cell_code`, `spec_code`, `total`, `normal`, `available`, `archive`, `quarantine`, `wait_scrap`, `scrap`, `stock_price`, `stock_amount`, `create_time`, `modify_time`) VALUES ");
        for (PriceResultBO bo : data) {
            builder.append("('")
                    .append(bo.getCellCode())
                    .append("','")
                    .append(bo.getSpecMdCode())
                    .append("',")
                    .append("'0', '0', '0', '0', '0', '0', '0','")
                    .append(bo.getPrice())
                    .append("', '0', NOW(), NOW()),");
        }
        builder.replace(builder.length() - 2, builder.length(), ";");
        try {
            Files.write(Paths.get(FileConstant.BASE_PATH_PRICE_RESULT + "待预置单价.sql"), builder.toString().getBytes());
        } catch (IOException e) {
            log.error("处理SQL文件异常：{}", e.getMessage(), e);
        }
    }


}
