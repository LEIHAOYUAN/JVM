package com.lei.jvm.service.excel.price.run;

import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lei.jvm.service.excel.price.ao.RequestDataAO;
import com.lei.jvm.service.excel.price.ao.base.SpecInfoModel;
import com.lei.jvm.service.excel.price.ao.base.StockCellModel;
import com.lei.jvm.service.excel.price.ao.base.StockDetailModel;
import com.lei.jvm.service.excel.price.bo.PriceResultBO;
import com.lei.jvm.service.excel.price.constant.FileConstant;
import com.lei.jvm.service.excel.price.service.RequestDataService;
import com.lei.jvm.service.excel.price.service.base.SpecInfoService;
import com.lei.jvm.service.excel.price.service.base.StockCellService;
import com.lei.jvm.service.excel.price.service.base.StockDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2021/7/30 21:17
 * @Version 1.0
 * @Description 解析excel
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        Map<String, StockCellModel> cellMap = readStockCellExcel();
        log.info("库存单位：{}", JSON.toJSONString(cellMap));
        Map<String, StockDetailModel> stockDetailMap = readStockDetailExcel();
        Map<String, String> specPriceMap = StockDetailService.buildSpecPriceMap(stockDetailMap);
        log.info("存在单价：{}",JSON.toJSONString(specPriceMap));
        // 解析业务excel，按照仓库维度解析
        Map<String, List<RequestDataAO>> requestMap = paddingDataHandler();
        log.info("请求数据：{}",JSON.toJSONString(requestMap));
        // TODO 根据StockDetail数据过滤已经存在单价的明细

        // TODO 对不存在的数据生成Excel，SQL文件


    }

    /**
     * 解析用户excel
     * @return
     */
    private static Map<String, List<RequestDataAO>> paddingDataHandler() {
        String filePath = FileConstant.BASE_PATH_REQUEST_DATA + "网购规格单价处理模板.xlsx";
        RequestDataService listener = new RequestDataService();
        ExcelReader excelReader = EasyExcel.read(filePath,RequestDataAO.class, listener).build();
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        if (CollectionUtils.isEmpty(sheets)) {
            log.error("获取sheet为空");
            return Maps.newHashMap();
        }
        Map<String, List<RequestDataAO>> sheetMap = Maps.newHashMap();
        List<RequestDataAO> sheetData = Lists.newArrayList();
        for (ReadSheet sheet : sheets) {
            // 先清空
            sheetData.clear();
            String sheetName = sheet.getSheetName();
            excelReader.read(sheet);
            sheetData = listener.getData();
            List<RequestDataAO> exisData = sheetMap.get(sheetName);
            if (CollectionUtils.isEmpty(exisData)) {
                sheetMap.put(sheetName, sheetData);
            } else {
                exisData.addAll(sheetData);
            }
        }
        // 关闭，清空临时文件
        excelReader.finish();
        return sheetMap;
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
