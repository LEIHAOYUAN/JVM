package com.lei.jvm.stu.spel;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.math.BigDecimal;
import java.util.List;

/**
 *  职能描述：测试SPEL解析表达式
 *  @author leihaoyuan
 *  @version 2022/8/16 11:09
 */
@Slf4j
public class SpELTest {


    public static void main(String[] args) {
        parse00();
//        parse01();
//        parse02();
//        parse03();
    }

    /**
     * 使用简单参数列表解析
     */
    private static void parse00() {
        // 表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 开始准备表达式运行环境
        EvaluationContext ctx = new StandardEvaluationContext();

        ctx.setVariable("username", "张三");
        ctx.setVariable("password", null);
        ctx.setVariable("hobbies", buildHobby());
        ctx.setVariable("sex", true);
        ctx.setVariable("user", buildUser());

        // 解析简单参数影射
        Expression expression1 = parser.parseExpression("#username");
        Expression expression2 = parser.parseExpression("#password");
        Expression expression3 = parser.parseExpression("#sex");
        Expression expression4 = parser.parseExpression("#hobbies");

        Object value1 = expression1.getValue(ctx, Object.class);
        Object value2 = expression2.getValue(ctx, Object.class);
        Object value3 = expression3.getValue(ctx, Object.class);
        Object value4 = expression4.getValue(ctx, Object.class);

        log.info("解析参数列表--------------------------------");
        log.info("parse00-简单参数获取结果：{}", value1);
        log.info("parse00-简单参数获取结果：{}", value2);
        log.info("parse00-简单参数获取结果：{}", value3);
        log.info("parse00-简单参数获取结果：{}", value4);

        // 对象属性
        Expression expression100 = parser.parseExpression("#user.name");
        Expression expression101 = parser.parseExpression("#user.age");
        Expression expression102 = parser.parseExpression("#user.amount");
        Expression expression103 = parser.parseExpression("#user.money");
        Expression expression104 = parser.parseExpression("#user.listHobby");


        Object value100 = expression100.getValue(ctx, Object.class);
        Object value101 = expression101.getValue(ctx, Object.class);
        Object value102 = expression102.getValue(ctx, Object.class);
        Object value103 = expression103.getValue(ctx, Object.class);
        Object value104 = expression104.getValue(ctx, Object.class);

        log.info("解析对象属性------------------------------");
        log.info("parse00=对象参数获取结果：{}", value100);
        log.info("parse00=对象参数获取结果：{}", value101);
        log.info("parse00=对象参数获取结果：{}", value102);
        log.info("parse00=对象参数获取结果：{}", value103);
        log.info("parse00=对象参数获取结果：{}", value104);
    }


    /**
     * 使用属性名解析单个属性
     */
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

    /**
     * 使用属性方法解析
     */
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

    /***
     * 使用属性名解析对象多参数
     */
    private static void parse03() {
        // 表达式解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 开始准备表达式运行环境
        EvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("user", buildUser());
        // 解析出一个表达式
        Expression expression1 = parser.parseExpression("#user.name");
        Expression expression2 = parser.parseExpression("#user.age");
        Expression expression3 = parser.parseExpression("#user.amount");
        Expression expression4 = parser.parseExpression("#user.money");

        Object value1 = expression1.getValue(ctx, Object.class);
        Object value2 = expression2.getValue(ctx, Object.class);
        Object value3 = expression3.getValue(ctx, Object.class);
        Object value4 = expression4.getValue(ctx, Object.class);
        log.info("parse03获取结果：{}", value1);
        log.info("parse03获取结果：{}", value2);
        log.info("parse03获取结果：{}", value3);
        log.info("parse03获取结果：{}", value4);
    }

    private static User buildUser() {
        User user = new User();
        user.setAge(null);
        user.setName("张三");
        user.setMoney(BigDecimal.valueOf(3.36854212));
        user.setListHobby(buildHobby());
        return user;
    }

    private static List<String> buildHobby() {
        List<String> listHobby = Lists.newArrayList();
        listHobby.add("羽毛球");
        listHobby.add("游泳");
        return listHobby;
    }

    @Data
    public static class User {

        private Integer age;

        private String name;

        private double amount;

        private BigDecimal money;

        private List<String> listHobby;

    }


}
