package com.lei.jvm.stu.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Data
public class BatchInfoModel implements Serializable {

    private static final long serialVersionUID = 4998004160563008009L;

    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 批次调整数量（用料单位）
     */
    private BigDecimal batchAdjustNum;
    /**
     * 批次调整后数量（用料单位）
     */
    private BigDecimal batchAdjustNumAfter;


    public static void main(String[] args) {
        BatchInfoModel aNull = JSON.parseObject("", BatchInfoModel.class);
        log.info("测试null反序列化：{}",aNull);
    }

    private static void testJSON(){
        BatchInfoModel model = new BatchInfoModel();
        model.setBatchNo("20210506");
        model.setBatchAdjustNum(BigDecimal.TEN);
        model.setBatchAdjustNumAfter(BigDecimal.ZERO);
        log.info("model:{}", JSON.toJSONString(model));

        BatchInfoModel model1 = new BatchInfoModel();
        model1.setBatchNo("20210506");
        model1.setBatchAdjustNum(BigDecimal.TEN);
        log.info("model1:{}", JSON.toJSONString(model1));

        List<BatchInfoModel> list = Lists.newArrayList();
        list.add(model);
        list.add(model1);
        log.info("list：{}",JSON.toJSONString(list));


        String jsonList = "[{\"batchAdjustNum\":10,\"batchAdjustNumAfter\":0,\"batchNo\":\"20210506\"},{\"batchAdjustNum\":10,\"batchNo\":\"20210506\"}]";
        List<BatchInfoModel> batchInfoModels = JSON.parseArray(jsonList, BatchInfoModel.class);
        for (BatchInfoModel batchInfoModel : batchInfoModels) {
            log.info("反序列化：{}",batchInfoModel.toString());
        }
    }


}