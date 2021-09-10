package com.base.utils.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @Author leihaoyuan
 * @Date 2021/3/18 14:38
 * @Version 1.0
 * @Description
 */
public class Test {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(StringBuilderBenchmark.class.getSimpleName()).build();
//        控制台输出
//                .output("E:/Benchmark.log")

        new Runner(options).run();
    }

}
