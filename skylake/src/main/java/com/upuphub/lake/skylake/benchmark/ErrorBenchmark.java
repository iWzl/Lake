package com.upuphub.lake.skylake.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1,time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2,time = 2,timeUnit = TimeUnit.SECONDS)
@Threads(6)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class ErrorBenchmark {

    public static void main(String[] args) throws RunnerException {
        String userDirPath = System.getProperty("user.dir");
        String benchmarkLogPath = String.format("%s/%s",userDirPath,"skylake/src/main/java/com/upuphub/lake/skylake/benchmark/ErrorBenchmark.log");
        Options options = new OptionsBuilder()
                .include(ErrorBenchmark.class.getSimpleName())
                .output(benchmarkLogPath)
                .build();
        new Runner(options).run();
    }

    private double PI = Math.PI;

    @Benchmark
    public void benchmarkNothing(){
        //do nothing
    }

    @Benchmark
    public double benchmarkRight(){
        return Math.log(PI);
    }

    @Benchmark
    public void benchmarkWrong(){
        Math.log(PI);
    }
}
