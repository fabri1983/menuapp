package org.fabri1983.menuapp.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

public class JMHLauncherJUnit {
	
	protected void launchBenchmark (String targetClass) throws RunnerException {
		
        Options opt = new OptionsBuilder()
                .include(targetClass) // Specify which benchmarks to run.
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.milliseconds(250))
                .warmupIterations(2)
                .measurementTime(TimeValue.milliseconds(250))
                .measurementIterations(2)
                .threads(4)
                .verbosity(VerboseMode.NORMAL)
                .forks(1) // how many VMs to spawn.
                .shouldFailOnError(true)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();
    }
	
	/**
	 * Initialize some variables that your benchmark code needs, but which you do not want to be part of the code your benchmark measures.
	 */
    @State(Scope.Thread)
    public static class BenchmarkState
    {
		@Setup(Level.Trial)
		public void initialize () {
		}
		
		@TearDown(Level.Trial)
        public void doTearDown() {
        }
    }
}
