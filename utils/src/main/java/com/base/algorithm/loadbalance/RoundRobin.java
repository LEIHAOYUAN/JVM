package com.base.algorithm.loadbalance;

import com.base.algorithm.loadbalance.model.Node;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author leihaoyuan
 * @Date 2021/12/28 10:42
 * @Version 1.0
 * @Description 简单轮询算法
 */
@Slf4j
public class RoundRobin {

    public static void main(String[] args) {
        List<Node> param = Lists.newArrayList();
        param.add(new Node("AAA"));
        param.add(new Node("BBB"));
        param.add(new Node("CCC"));
        param.add(new Node("DDD"));
        for (int i = 0; i < 20; i++) {
            log.info("第{}次-轮询结果：{}", i, choose(param));
        }
    }

    /**
     * 全局变量记录上次下标
     */
    private final static AtomicInteger nextIndex = new AtomicInteger(-1);

    /**
     * 简单轮询算法实现
     *
     * @param param
     * @return
     */
    public static Node choose(List<Node> param) {
        if (CollectionUtils.isEmpty(param)) {
            log.warn("无可选数据......");
            return null;
        }
        return param.get(incrementAndGetNext(param.size()));
    }

    /**
     * 获取下一次下标
     *
     * @param size 集合大小
     * @return 集合下标
     */
    private static int incrementAndGetNext(int size) {
        for (; ; ) {
            //获取当前的服务索引值
            int current = nextIndex.get();
            //通过取余的方式计算下一个索引值
            int next = (current + 1) % size;
            //通过 CAS 设置下一个搜索引值（解决并发索引值可能重复的问题）
            if (nextIndex.compareAndSet(current, next)) {
                return next;
            }
        }
    }


}
