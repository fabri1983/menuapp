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
	
	public static void launchBenchmark (String targetClass) throws RunnerException {
		
		// this needed in order to avoid re running of @Test
		BenchmarkState.junitInitializaed = true;
		
        Options opt = new OptionsBuilder()
                .include(targetClass)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupIterations(2)
                .warmupTime(TimeValue.milliseconds(250))
                .measurementIterations(2)
                .measurementTime(TimeValue.milliseconds(250))
                .threads(4)
                .forks(1) // how many VMs to spawn.
                .verbosity(VerboseMode.NORMAL)
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
    	static boolean junitInitializaed = false;
    	
    	public static boolean isJunitInitialized () {
    		return junitInitializaed;
    	}
    	
		@Setup(Level.Trial)
		public void initialize () {
		}
		
		@TearDown(Level.Trial)
        public void doTearDown() {
        }
    }
}
