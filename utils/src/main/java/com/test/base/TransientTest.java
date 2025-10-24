package com.test.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/11/10 11:07
 * @Version 1.0
 * @Description Transient测试
 */
@Slf4j
@Data
public class TransientTest implements Serializable {

    private static final long serialVersionUID = -4720187174176955837L;

    private String name;

    @Transient
    private String remark;

    private transient String notes;

    public static void main(String[] args) {
        TransientTest param = new TransientTest();
        param.setName("AAA");
        param.setRemark("备注信息");
        param.setNotes("附加信息");
        log.info("transient序列化测试：{}", JSON.toJSONString(param));
    }


}
