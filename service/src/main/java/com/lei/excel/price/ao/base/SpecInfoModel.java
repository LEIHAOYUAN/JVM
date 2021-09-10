package com.lei.excel.price.ao.base;

import lombok.Data;

/**
 * @Author leihaoyuan
 * @Date 2021/7/31 14:38
 * @Version 1.0
 * @Description
 */
@Data
public class SpecInfoModel {

    private String specCode;
    private String specName;
    private String specMdCode;


    /*
    SELECT  spec_code AS specCode,name AS specName,code AS specMdCode FROM t_material_spec WHERE spec_code IN('4');
     */
}
