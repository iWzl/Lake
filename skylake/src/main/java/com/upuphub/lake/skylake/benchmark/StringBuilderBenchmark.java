package com.upuphub.lake.skylake.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 比较字符串直接相加和StringBuilder的效率
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2020/5/2 21:44
 */

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1,time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10,time = 10,timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class StringBuilderBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(StringBuilderBenchmark.class.getSimpleName())
                .output("E:/0_JavaProject/1_OpenHub/lake/skylake/src/main/java/com/upuphub/lake/skylake/benchmark/StringBenchmark.log")
                .build();
        new Runner(options).run();
    }


    @Benchmark
    public void testStringAdd() {
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        print(a);
    }


    @Benchmark
    public void testStringBuilderAdd() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        print(sb.toString());
    }

    private void print(String a) {
    }
}
