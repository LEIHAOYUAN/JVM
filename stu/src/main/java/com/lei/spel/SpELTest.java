package com.lei.spel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 *  职能描述：测试SPEL解析表达式
 *  @author leihaoyuan
 *  @version 2022/8/16 11:09
 */
@Slf4j
public class SpELTest {


    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("getInput()");
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        User user = new User("张三");
        // 设置需要执行方法的类
        ctx.setRootObject(user);
        String value = expression.getValue(ctx, String.class);
        log.info("获取结果：{}", value);
    }

    public static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String getInput() {
            return "我叫：" + this.name;
        }
    }


}
