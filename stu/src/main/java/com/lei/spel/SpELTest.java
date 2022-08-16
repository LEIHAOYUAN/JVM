package com.lei.spel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/8/16 11:09
 */
@Slf4j
public class SpELTest {


    public static void main(String[] args) {
        ExpressionParser parser =  new SpelExpressionParser();
        String template = "#test";
        Expression expression = parser.parseExpression(template);

        // expression context
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("test","值");
        log.info("解析到属性：{}",expression.getValue(context,Object.class));


    }



}
