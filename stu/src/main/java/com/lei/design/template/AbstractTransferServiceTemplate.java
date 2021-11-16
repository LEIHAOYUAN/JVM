package com.lei.design.template;

import javax.naming.Context;

/**
 * @Author leihaoyuan
 * @Date 2021/11/16 19:50
 * @Version 1.0
 * @Description 命令服务模板
 */
public abstract class AbstractTransferServiceTemplate {

    /**
     * 参数校验
     * @param context 上下文
     */
    abstract void validParam(Context context);

    /**
     * 推送WMS
     * @param context 上下文
     */
    abstract void pushToWms(Context context);

    /**
     * 调整库存
     * @param context 上下文
     */
    abstract void adjustStock(Context context);

    /**
     * 记录操作日志
     * @param context 上下文
     */
    public void recordBizLog(Context context){}

    /**
     * 业务操作
     * @param context 上下文
     */
    abstract void command(Context context);

    public void templateMethod(Context context){
        // 参数校验
        validParam(context);
        // 执行业务操作
        command(context);
        // 调整库存
        adjustStock(context);
        // 推送三方
        pushToWms(context);
        // 记录日志
        recordBizLog(context);
    }



}
