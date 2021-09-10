package com.lei.excel.price.service.base;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lei.excel.price.ao.base.StockCellModel;
import com.lei.excel.price.constant.FileConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

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
public class StockCellService extends AnalysisEventListener<StockCellModel> {

    private List<StockCellModel> listModel = Lists.newArrayList();


    public StockCellService() {
        parseExcel();
    }

    @Override
    public void invoke(StockCellModel model, AnalysisContext analysisContext) {
        listModel.add(model);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("【库存单位】表头信息：{}", headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<StockCellModel> getData() {
        return listModel;
    }

    public Map<String, StockCellModel> getMap() {
        if (CollectionUtils.isEmpty(this.listModel)) {
            log.warn("【库存单位】excel数据为空");
            return Maps.newHashMap();
        }
        log.info("【库存单位】excel结果：{}", JSON.toJSONString(this.listModel));
        return this.listModel.stream().collect(Collectors.toMap(StockCellModel::getName, i -> i, (t, t2) -> t2));
    }

    private void parseExcel() {
        EasyExcel.read(FileConstant.BASE_PATH_PRICE + "仓库信息.xlsx", StockCellModel.class, this).sheet().doRead();
    }

}
