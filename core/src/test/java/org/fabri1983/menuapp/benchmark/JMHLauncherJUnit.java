package org.fabri1983.menuapp.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

public class JMHLauncherJUnit {
	
	static boolean junitInitializaed = false;
	
	public static boolean isJunitInitialized() {
		return junitInitializaed;
	}
	
	public static void launchBenchmark(String targetClass) throws RunnerException {
		
		// this needed in order to avoid re running of @Test
		junitInitializaed = true;
		
        Options opt = new OptionsBuilder()
                .include(targetClass)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupIterations(2)
                .warmupTime(TimeValue.milliseconds(250))
                .measurementIterations(2)
                .measurementTime(TimeValue.milliseconds(250))
                .threads(4)
                .forks(2) // how many VMs to spawn. Helps estimate the run-to-run variance
                .verbosity(VerboseMode.NORMAL)
                .shouldFailOnError(true)
                .shouldDoGC(false)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();
    }
	
}
