package com.lei.jvm.stu.clone;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/25 15:33
 */
@Slf4j
public class CloneTest {

    public static void main(String[] args) {
        Info source = new Info();
        source.setName("AAA");
        Info target = new Info();
        BeanUtil.copyProperties(source, target);
        log.info("对象拷贝结果={}", JSON.toJSONString(target));
    }

}
