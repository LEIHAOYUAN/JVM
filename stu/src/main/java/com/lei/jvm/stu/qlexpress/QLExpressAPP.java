package com.lei.jvm.stu.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：QLExpress语法使用
 *  https://github.com/alibaba/QLExpress
 *  @author leihaoyuan
 *  @version 2023/2/6 14:06
 */
@Slf4j
public class QLExpressAPP {

    public static void main(String[] args) throws Exception {
        // 简单表达式测试
        // simpleExpress("10 * 10 + 1 + 2 * 3 + 5 * 2");
        // 简单宏定义测试
        simpleMacro();

    }

    private static void simpleExpress(String express) throws Exception {
        ExpressRunner runner = new ExpressRunner(true, true);
        Object r = runner.execute(express, null, null, false, false);
        log.info("表达式计算：{}={}", express, r);
    }

    private static void simpleMacro() throws Exception {
        ExpressRunner runner = new ExpressRunner(false, true);
        runner.addMacro("计算平均成绩", "(语文+数学+英语)/3.0");
        runner.addMacro("是否优秀", "计算平均成绩>90");
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("语文", 30);
        context.put("数学", 20);
        context.put("英语", 50);
        Object r1 = runner.execute("计算平均成绩", context, null, false, false);
        Object r2 = runner.execute("是否优秀", context, null, false, false);

        log.info("计算平均成绩={}", r1);
        log.info("是否优秀={}", r2);
    }


}
