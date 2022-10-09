package com.lei.jvm.stu.time;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author leihaoyuan
 * @Date 2021/7/22 13:35
 * @Version 1.0
 * @Description
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
//120轮预热，充分利用JIT的编译优化技术
@Warmup(iterations = 120, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.MICROSECONDS)
//线程数：CPU*2（计算复杂型，也有CPU+1的说法）
@Threads(8)
@Fork(1)
@State(Scope.Benchmark)
public class CurrentTimeillsJMTTest {

    public static void main(String[] args) throws RunnerException {
        testNTime(10000);
    }

    private static void testNTime(int num) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(CurrentTimeillsJMTTest.class.getSimpleName())
                .measurementIterations(num)
                .output("E://testRecord.log")
                .build();
        new Runner(options).run();
    }


    /**
     * System.currentMillisTime测试
     *
     * @return 将结果返回是为了防止死码消除（编译器将 无引用的变量 当成无用代码优化掉）
     */
    @Benchmark
    public long testSystem() {
        return System.currentTimeMillis();
    }

    /**
     * 缓存时钟测试
     *
     * @return
     */
    @Benchmark
    public long testCacheClock() {
        return CurrentTimeillsJMTTest.CacheClock.currentTimeMillis();
    }

    /**
     * 缓存时钟，缓存System.currentTimeMillis()的值，每隔1s更新一次
     */
    public static class CacheClock {
        private static ScheduledExecutorService timer = new ScheduledThreadPoolExecutor(1);
        private static volatile long timeMilis;

        static {
            timer.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    timeMilis = System.currentTimeMillis();
                }
            }, 0, 1000, TimeUnit.MILLISECONDS);
        }

        public static long currentTimeMillis() {
            return timeMilis;
        }
    }


}
