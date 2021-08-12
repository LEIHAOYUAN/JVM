package com.excel.price.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.excel.price.ao.RequestDataAO;
import com.excel.price.ao.base.SpecInfoModel;
import com.excel.price.constant.FileConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/7/31 14:49
 * @Version 1.0
 * @Description
 */
@Slf4j
public class RequestDataService extends AnalysisEventListener<RequestDataAO> {

    private List<RequestDataAO> listModel = Lists.newArrayList();

    /**
     * 每次解析一行数据调用
     * @param model
     * @param analysisContext
     */
    @Override
    public void invoke(RequestDataAO model, AnalysisContext analysisContext) {
        listModel.add(model);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("【业务待处理数据】表头信息：{}", headMap);
    }

    /**
     * 所有数据解析完调用
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<RequestDataAO> getData() {
        return listModel;
    }

    public Map<String, RequestDataAO> getMap() {
        if (CollectionUtils.isEmpty(this.listModel)) {
            log.warn("【业务待处理数据】excel数据为空");
            return Maps.newHashMap();
        }
        log.info("【业务待处理数据】excel结果：{}", JSON.toJSONString(this.listModel));
        return this.listModel.stream().collect(Collectors.toMap(RequestDataAO::getSpecCode, i -> i, (t, t2) -> t2));
    }



}
