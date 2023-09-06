package com.lei.jvm.spring.utils;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.Operator;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里巴巴QLExpress表达式工具类
 *
 * @author leihaoyuan
 * @version 2023/9/6 10:54
 */
@Slf4j
public class QLExpressUtil {

    private ExpressRunner runner;
    private IExpressContext context;

    public QLExpressUtil() {
        runner = new ExpressRunner();
        context = new DefaultContext();
    }

    public void addFunction(String functionName) {
        try {

            runner.addFunction(functionName, new Operator() {
                @Override
                public Object executeInner(Object[] objects) throws Exception {
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object executeExpression(String expression) {
        try {
            return runner.execute(expression, context, null, true, false);
        } catch (Exception e) {
            log.error("执行异常表达式=[{}],异常={}", expression, e.getMessage(), e);
            return null;
        }
    }


}
