package com.lei.jvm.utils.base.algorithm.list;


import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 数据源分配规则
 *
 * @author leihaoyuan
 * @version 2024/11/14 18:04
 */
@Slf4j
@Data
public class RuleDomain implements Serializable {

    public static void main(String[] args) {
        RuleDomain ruleDomain = new RuleDomain();
        ruleDomain.setLastDWCode("3");
        ruleDomain.setDwCodeList(Lists.newArrayList("1", "2", "3"));
        log.info("获取到下一个元素结果={}", ruleDomain.getNextDWCode());
    }

    private static final long serialVersionUID = 624543820806015419L;

    /**
     * 上次分配的数据库编码
     */
    private String lastDWCode;

    /**
     * 数仓分配catalog集合
     */
    private List<String> dwCodeList = Lists.newArrayList();


    /**
     * 获取下一个循环元素
     *
     * @return DB编号
     */
    public String getNextDWCode() {
        if (CollectionUtils.isEmpty(this.dwCodeList)) {
            throw new IllegalArgumentException("配置非法");
        }

        if (StringUtils.isBlank(this.lastDWCode)) {
            return this.dwCodeList.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("配置非法"));
        }

        if (!this.dwCodeList.contains(this.lastDWCode)) {
            throw new IllegalArgumentException("配置非法");
        }
        // 获取到当前下标
        int index = this.dwCodeList.indexOf(this.lastDWCode);
        // 循环获取下一个元素
        index = (index + 1) % this.dwCodeList.size();
        return this.dwCodeList.get(index);
    }
}
