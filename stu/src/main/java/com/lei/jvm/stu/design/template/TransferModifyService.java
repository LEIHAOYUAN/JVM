package com.lei.jvm.stu.design.template;

import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;

/**
 * @Author leihaoyuan
 * @Date 2021/11/16 20:04
 * @Version 1.0
 * @Description 修改转移单服务
 */
@Slf4j
public class TransferModifyService extends AbstractTransferServiceTemplate{

    @Override
    void validParam(Context context) {

    }

    @Override
    public void pushToWms(Context context) {

    }

    @Override
    void adjustStock(Context context) {
        log.info("审核转移单，无需调整库存");
    }

    @Override
    void command(Context context) {

    }
}
