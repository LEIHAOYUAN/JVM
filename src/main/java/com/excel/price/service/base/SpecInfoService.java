package com.excel.price.service.base;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
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
public class SpecInfoService extends AnalysisEventListener<SpecInfoModel> {

    private List<SpecInfoModel> listModel = Lists.newArrayList();

    public SpecInfoService() {
        parseExcel();
    }

    @Override
    public void invoke(SpecInfoModel model, AnalysisContext analysisContext) {
        listModel.add(model);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("【货物规格】表头信息：{}", headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<SpecInfoModel> getData() {
        return listModel;
    }

    public Map<String, SpecInfoModel> getMap() {
        if (CollectionUtils.isEmpty(this.listModel)) {
            log.warn("【货物规格】excel数据为空");
            return Maps.newHashMap();
        }
        log.info("【货物规格】excel结果：{}", JSON.toJSONString(this.listModel));
        return this.listModel.stream().collect(Collectors.toMap(SpecInfoModel::getSpecCode, i -> i, (t, t2) -> t2));
    }

    private void parseExcel() {
        EasyExcel.read(FileConstant.BASE_PATH_PRICE + "规格信息.xlsx", SpecInfoModel.class, this).sheet().doRead();
    }


}
