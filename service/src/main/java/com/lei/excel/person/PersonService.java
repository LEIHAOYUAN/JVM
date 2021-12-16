package com.lei.excel.person;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import com.lei.excel.price.ao.base.StockCellModel;
import com.lei.excel.price.constant.FileConstant;
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
public class PersonService extends AnalysisEventListener<Person> {

    private List<Person> listModel = Lists.newArrayList();


    public PersonService() {
        parseExcel();
    }

    @Override
    public void invoke(Person model, AnalysisContext analysisContext) {
        listModel.add(model);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("表头信息：{}", headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<Person> getData() {
        return listModel;
    }

    private void parseExcel() {
        EasyExcel.read(FileConstant.BASE_PATH_PRICE + "增加部门ID.xlsx", Person.class, this).sheet().doRead();
    }

}
