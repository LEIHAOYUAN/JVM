package com.excel.price.constant;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/7/31 21:35
 * @Version 1.0
 * @Description
 */
@Data
public class FileConstant implements Serializable {

    private static final long serialVersionUID = 7064646946512989093L;

    /**
     * 预置单价基础路径
     */
    public static final String BASE_PATH_PRICE = "D:\\工作文档\\预置单价\\";
}
