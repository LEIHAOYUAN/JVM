package com.lei.jvm.utils.base.utils.equals;

import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * @author leihaoyuan
 * @version 2024/1/23 17:15
 */
@Slf4j
public class AttributionEquals {


    public static void main(String[] args) {
        log.info("比较={}",Boolean.TRUE.equals(null));
    }




    public static boolean guavaEquals(Object obj1,Object obj2){
        return EqualsBuilder.reflectionEquals(obj1,obj2);
    }


}
