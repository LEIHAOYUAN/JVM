package com.excel.price.service.base;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.excel.price.ao.base.StockDetailModel;
import com.excel.price.constant.FileConstant;
import com.google.common.collect.Lists;
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
 * @Date 2021/7/30 21:49
 * @Version 1.0
 * @Description
 */
@Slf4j
public class StockDetailService extends AnalysisEventListener<StockDetailModel> {

    private List<StockDetailModel> listModel = Lists.newArrayList();

    public StockDetailService() {
        parseExcel();
    }

    @Override
    public void invoke(StockDetailModel model, AnalysisContext analysisContext) {
        this.listModel.add(model);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("【库存明细】表头信息：{}", headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<StockDetailModel> getData() {
        return this.listModel;
    }

    public Map<String, StockDetailModel> getMap() {
        if (CollectionUtils.isEmpty(this.listModel)) {
            log.warn("【库存明细】excel数据为空");
            return Maps.newHashMap();
        }
        log.info("【库存明细】excel结果：{}", JSON.toJSONString(this.listModel));
        return this.listModel.stream().collect(Collectors.toMap(StockDetailModel::getCellName, i -> i, (t, t2) -> t2));
    }

    private void parseExcel() {
        EasyExcel.read(FileConstant.BASE_PATH_PRICE + "单价信息.xlsx", StockDetailModel.class, this).sheet().doRead();
    }

    public static Map<String, String> buildSpecPriceMap(Map<String, StockDetailModel> stockDetailMap) {
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
