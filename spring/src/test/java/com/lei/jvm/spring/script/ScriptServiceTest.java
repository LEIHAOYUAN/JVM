package com.lei.jvm.spring.script;

import com.lei.jvm.spring.moudle.script.ScriptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/3/6 10:37
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScriptServiceTest {

    @Autowired
    private ScriptService scriptService;

    @Test
    public void testScript() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String script = "function add(a, b) { return a + b; }";
        engine.eval(script);
        Invocable invocable = (Invocable) engine;
        Object result = invocable.invokeFunction("add", 2, 3);
        System.out.println(result);
    }


}
