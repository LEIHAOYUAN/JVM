package com.excel.price.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/7/30 21:11
 * @Version 1.0
 * @Description
 */
@Data
public class StockDetailModel implements Serializable {

    private static final long serialVersionUID = 8390307652932011087L;

    private String cellName;
    private String cellCode;
    private String specMdCode;
    private String specCode;
    private String specName;
    private String stockPrice;


    /*
SELECT cell.name AS cellName,detail.cell_code AS cellCode,detail.spec_code AS specMdCode,spec.spec_code AS specCode,spec.name AS specName,detail.stock_price AS stockPrice,detail.total
FROM t_stock_detail AS detail
LEFT JOIN t_stock_cell AS cell ON cell.code = detail.cell_code
LEFT JOIN t_material_spec AS spec ON spec.code = detail.spec_code
WHERE detail.cell_code IN (SELECT code FROM t_stock_cell) AND spec.spec_code IN (
'GS0280-002',
'GS0514-001',
'GS0254-001',
'GS0490-001',
'GS0514-001',
'GS0516-001'
);
     */


}
