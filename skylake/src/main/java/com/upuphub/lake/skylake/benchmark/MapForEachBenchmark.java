package com.upuphub.lake.skylake.benchmark;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * Map 遍历方式的执行效率测试
 *
 * 压力测试结果
 *
 * Benchmark                                 Mode  Cnt       Score      Error   Units
 * MapForEachBenchmark.forEntrySet          thrpt   60  112688.543 ± 7006.665  ops/ms
 * MapForEachBenchmark.forEntrySetIterator  thrpt   60   84463.782 ± 1687.194  ops/ms
 * MapForEachBenchmark.forKeySet            thrpt   60   41092.944 ± 1574.240  ops/ms
 * MapForEachBenchmark.forKeySetIterator    thrpt   60   55380.712 ± 2889.469  ops/ms
 * MapForEachBenchmark.forLambda            thrpt   60   45413.544 ±  595.175  ops/ms
 * MapForEachBenchmark.forParallelStream    thrpt   60    4748.341 ±  114.635  ops/ms
 * MapForEachBenchmark.forStreams           thrpt   60   45232.937 ±  180.460  ops/ms
 *
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1,time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 30,time = 20,timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class MapForEachBenchmark {
    private static final Map<String,String> stringMap = new HashMap<>(10);

    static {
        for (int i = 0; i < 10; i++) {
            stringMap.put(String.format("key-%s",i),String.format("value-%s",i));
        }
    }

    public static void main(String[] args) throws RunnerException {
        String userDirPath = System.getProperty("user.dir");
        String benchmarkLogPath = String.format("%s/%s",userDirPath,"skylake/src/main/java/com/upuphub/lake/skylake/benchmark/MapForEachBenchmark.log");
        Options options = new OptionsBuilder()
                .include(MapForEachBenchmark.class.getSimpleName())
                .output(benchmarkLogPath)
                .build();
        new Runner(options).run();
    }


    @Benchmark
    public void forKeySet() {
        for (String key : stringMap.keySet()) {
            print(key,stringMap.get(key));
        }
    }

    @Benchmark
    public void forLambda() {
        stringMap.forEach((key,value)->print(key,stringMap.get(key)));
    }

    @Benchmark
    public void forEntrySet() {
        for (Map.Entry<String, String> stringEntry : stringMap.entrySet()) {
            print(stringEntry.getKey(),stringEntry.getValue());
        }
    }

    @Benchmark
    public void forEntrySetIterator() {
        Iterator<Map.Entry<String, String>> iterator = stringMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> stringEntry = iterator.next();
            print(stringEntry.getKey(),stringEntry.getValue());
        }
    }

    @Benchmark
    public void forKeySetIterator() {
        Iterator<String> iterator = stringMap.keySet().iterator();
        while (iterator.hasNext()) {
            print(iterator.next(),stringMap.get(iterator.next()));
        }
    }

    @Benchmark
    public void forStreams() {
        stringMap.entrySet().stream().forEach((stringStringEntry -> {
            print(stringStringEntry.getKey(),stringStringEntry.getValue());
        }));
    }

    @Benchmark
    public void forParallelStream() {
        stringMap.entrySet().parallelStream().forEach((stringStringEntry -> {
            print(stringStringEntry.getKey(),stringStringEntry.getValue());
        }));
    }


    private void print(String key,String value){
    }


}
