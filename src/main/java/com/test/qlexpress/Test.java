package com.test.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/2/4 16:18
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
//        test01();
        test02();
    }

    private static void test01() {
        try {
            // 定义规则
            DefaultContext<String, Object> context = new DefaultContext<String, Object>();
            context.put("a", 1);
            context.put("b", 2);
            context.put("c", 3);
            String express = "a+b*c";
            // 执行
            ExpressRunner runner = new ExpressRunner();
            Object r = runner.execute(express, context, null, true, false);
            System.out.println(r);
        } catch (Exception ex) {
            log.error("异常信息：{}", ex.getMessage(), ex);
        }
    }

    private static void test02() {
        try {
            ExpressRunner runner = new ExpressRunner();
            runner.addOperatorWithAlias("如果", "if", null);
            runner.addOperatorWithAlias("则", "then", null);
            runner.addOperatorWithAlias("否则", "else", null);
            String exp = "如果  (语文+数学+英语>270) 则 {return 1;} 否则 {return 0;}";
            DefaultContext<String, Object> context = new DefaultContext<String, Object>();
            Object res = runner.execute(exp, context, null, false, false, null);
            System.out.println(res);
        } catch (Exception ex) {
            log.error("异常信息：{}", ex.getMessage(), ex);
        }
    }


}
