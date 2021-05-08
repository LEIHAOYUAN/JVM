package com.util.valid;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.util.exception.StockCommonException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * @Description: 业务异常校验工具
 * @Author: 苏展林 zhanlin.su@joymo.tech
 * @Date: 2021-01-21 15:14
 */
public class ValidUtil {


    /**
     * 非空校验
     *
     * @param o   校验对象
     * @param msg 异常消息
     */
    public static void notNull(Object o, String msg) {
        if (o == null) {
            throw new StockCommonException(msg);
        }
    }


    /**
     * 非空校验
     *
     * @param o   校验对象
     * @param msg 异常消息
     */
    public static void notEmpty(String o, String msg) {
        if (StrUtil.isEmpty(o)) {
            throw new StockCommonException(msg);
        }
    }

    /**
     * 非空校验
     *
     * @param o   校验对象
     * @param msg 异常
     */
    public static void notBlank(String o, String msg) {
        if (StrUtil.isBlank(o)) {
            throw new StockCommonException(msg);
        }
    }


    /**
     * 非空校验
     *
     * @param o   校验对象
     * @param msg 异常消息
     */
    public static void notEmpty(Collection o, String msg) {
        if (CollUtil.isEmpty(o)) {
            throw new StockCommonException(msg);
        }
    }


    /**
     * 非空校验
     *
     * @param o   校验对象
     * @param msg 异常消息
     */
    public static void notEmpty(Map o, String msg) {
        if (MapUtil.isEmpty(o)) {
            throw new StockCommonException(msg);
        }
    }


    /**
     * 正反校验
     *
     * @param o   校验对象
     * @param msg 异常消息
     */
    public static void isTrue(boolean o, String msg) {
        if (o) {
            throw new StockCommonException(msg);
        }
    }

    /**
     * 正反校验
     *
     * @param o   校验对象
     * @param msg 异常消息
     */
    public static void isFalse(boolean o, String msg) {
        isTrue(!o, msg);
    }

    /**
     * 不在此范围内
     *
     * @param o    校验对象
     * @param msg  异常消息
     * @param ages 范围
     */
    public static void notIn(Integer o, String msg, Integer... ages) {
        notNull(o, msg);
        if (ages != null) {
            boolean exist = false;
            for (Integer age : ages) {
                if (exist = (o.equals(age))) {
                    break;
                }
            }
            if (!exist) {
                throw new StockCommonException(msg);
            }
        }
    }

    /**
     * 长度大于
     *
     * @param o      校验对象
     * @param length 指定长度
     * @param msg    异常消息
     */
    public static void lengthGreaterThen(String o, int length, String msg) {
        if (StrUtil.isNotEmpty(o) && o.length() > length) {
            throw new StockCommonException(msg);
        }
    }

    /**
     * 小于等于0则抛出异常
     *
     * @param param 校验参数
     * @param msg   异常消息
     */
    public static void notLessThanZero(Long param, String msg) {
        if (null == param || param < 0) {
            throw new StockCommonException(msg);
        }
    }

    /**
     * 小于等于0则抛出异常
     *
     * @param param 校验参数
     * @param msg   异常消息
     */
    public static void notLessThanZero(BigDecimal param, String msg) {
        if (null == param || BigDecimal.ZERO.compareTo(param) > 0) {
            throw new StockCommonException(msg);
        }
    }

    /**
     * 小于0则抛出异常
     *
     * @param param 校验参数
     * @param msg   异常消息
     */
    public static void notLessThanZero(BigDecimal param, String msg, Object... msgParams) {
        if (null == param || BigDecimal.ZERO.compareTo(param) > 0) {
            throw new StockCommonException(msg, msgParams);
        }
    }


    /**
     * 邮箱校验
     *
     * @param email 邮箱
     * @return
     */
    public static boolean isEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        return Validator.isEmail(email);
    }

}
