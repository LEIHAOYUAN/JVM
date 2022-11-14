package com.lei.jvm.groovy;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.Test;

/**
 *  职能描述：groovy脚本测试
 *  @author leihaoyuan
 *  @version 2022/11/14 15:11
 */
public class GroovyScriptTest {


    @Test
    public void testScript() {
        //创建GroovyShell
        GroovyShell groovyShell = new GroovyShell();
        //装载解析脚本代码
        Script script = groovyShell.parse("package groovy\n" + "\n" + "def HelloWorld(){\n" + "    println \"hello world\"\n" + "}");
        //执行
        script.invokeMethod("HelloWorld", null);
    }

}
