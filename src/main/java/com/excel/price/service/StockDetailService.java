package com.excel.price.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.excel.price.ao.StockDetailModel;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2021/7/30 21:49
 * @Version 1.0
 * @Description
 */
@Slf4j
public class StockDetailService extends AnalysisEventListener<StockDetailModel> {

    private List<StockDetailModel> listModel = Lists.newArrayList();


    @Override
    public void invoke(StockDetailModel model, AnalysisContext analysisContext) {
        this.listModel.add(model);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("表头信息：{}",headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<StockDetailModel> getData(){
        return listModel;
    }

}
