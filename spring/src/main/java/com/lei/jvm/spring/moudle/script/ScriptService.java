package com.lei.jvm.spring.moudle.script;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/3/6 10:33
 */
@Slf4j
@Service
public class ScriptService {


    public void execScript(String script) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            engine.eval(script);
        } catch (ScriptException e) {
            log.error("脚本执行异常={}", e.getMessage(), e);
        }
    }

}
