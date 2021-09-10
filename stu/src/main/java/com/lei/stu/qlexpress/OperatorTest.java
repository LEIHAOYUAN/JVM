package com.lei.stu.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/2/4 16:38
 * @Version 1.0
 * @Description
 */
@Slf4j
public class OperatorTest {

    public static void main(String[] args) {


    }

    private static void addOperator() {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<String, Object>();
            runner.addOperator("join", new JoinOperator());
            Object r = runner.execute("1 join 2 join 3", context, null, false, false);
            System.out.println(r);
        } catch (Exception ex) {
            log.error("异常信息：{}", ex.getMessage(), ex);
        }
    }

    private static void replaceOperator() {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<String, Object>();
            runner.replaceOperator("+", new JoinOperator());
            Object r = runner.execute("1 + 2 + 3", context, null, false, false);
            System.out.println(r);
        } catch (Exception ex) {
            log.error("异常信息：{}", ex.getMessage(), ex);
        }
    }

    private static void addFunction() {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<String, Object>();
            runner.addFunction("join", new JoinOperator());
            Object r = runner.execute("join(1,2,3)", context, null, false, false);
            System.out.println(r);
        } catch (Exception ex) {
            log.error("异常信息：{}", ex.getMessage(), ex);
        }
    }


}
