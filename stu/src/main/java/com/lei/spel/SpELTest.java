package com.lei.spel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
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
        parse01();
        parse02();
    }

    private static void parse01() {
        // 表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 解析出一个表达式
        Expression expression = parser.parseExpression("#user.name");
        // 开始准备表达式运行环境
        EvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("user", buildUser());
        String value = expression.getValue(ctx, String.class);
        log.info("parse01获取结果：{}", value);
    }

    private static void parse02() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("getName()");
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        User user = buildUser();
        // 设置需要执行方法的类
        ctx.setRootObject(user);
        String value = expression.getValue(ctx, String.class);
        log.info("parse02获取结果：{}", value);
    }

    private static User buildUser(){
        User user = new User();
        user.setAge(100);
        user.setName("张三");
        return user;
    }

    public static class User {

        private Integer age;

        private String name;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
