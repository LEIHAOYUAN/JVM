package com.lei.jvm.service.excel.price.ao.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/7/30 21:57
 * @Version 1.0
 * @Description
 */
@Data
public class StockCellModel implements Serializable {

    private static final long serialVersionUID = -4365016130683344377L;

    private String name;
    private String code;

    /*
    select name,code from t_stock_cell where cell_type_code = 30200000025
     */
}
