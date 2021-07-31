package com.excel.price.ao;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/7/30 22:47
 * @Version 1.0
 * @Description
 */
@Data
public class RequestDataModel implements Serializable {

    private static final long serialVersionUID = -3970056631372307125L;

    private String id;
    private String specCode;
    private String specName;
    private String remark;
    private String photo;
    private String number;
    private String channel;

}
